package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.models.astronauts.*;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private AstronautRepository astronautRepository;
    private PlanetRepository planetRepository;
    private int exploredPlanetsCount;

    public ControllerImpl() {

        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {

        Astronaut astronaut = createAstronaut(type, astronautName);

        astronautRepository.add(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_ADDED,type, astronautName);
    }

    private Astronaut createAstronaut(String type, String astronautName) {

        switch (type) {

            case "Biologist":
                return new Biologist(astronautName);
            case "Geodesist":
                return new Geodesist(astronautName);
            case "Meteorologist":
                return new Meteorologist(astronautName);

        }

        return null;
    }

    @Override
    public String addPlanet(String planetName, String... items) {

        Planet planet = new PlanetImpl(planetName);
        planet.getItems().addAll(Arrays.asList(items));
        planetRepository.add(planet);

        return String.format(ConstantMessages.PLANET_ADDED,planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {

        Astronaut astronaut = astronautRepository.findByName(astronautName);

        astronautRepository.remove(astronaut);
        return String.format(ConstantMessages.ASTRONAUT_RETIRED,astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {

        Mission mission = new MissionImpl();

        Planet planet = planetRepository.findByName(planetName);

        List<Astronaut> astronautsToSendOnAMission = astronautRepository.getModels()
                .stream().filter(a -> a.getOxygen() > 60).collect(Collectors.toList());

        mission.explore(planet, astronautsToSendOnAMission);
        long countAstronautsWithoutOxygen = astronautsToSendOnAMission.stream()
                .filter(a -> !a.canBreath()).count();

        exploredPlanetsCount++;

        return String.format(ConstantMessages.PLANET_EXPLORED, planetName, countAstronautsWithoutOxygen);
    }

    @Override
    public String report() {

        // "{exploredPlanetsCount} planets were explored!"
        //"Astronauts info:"
        //"Name: {astronautName One}"
        //"Oxygen: {astronautOxygen One}"
        //"Bag items: {bagItem1, bagItem2, bagItem3, …, bagItemn \ "none"}"
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(ConstantMessages.REPORT_PLANET_EXPLORED, exploredPlanetsCount));
        sb.append(System.lineSeparator());
        sb.append(ConstantMessages.REPORT_ASTRONAUT_INFO);
        sb.append(System.lineSeparator());

        astronautRepository.getModels().forEach(a -> {
                    sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_NAME, a.getName()));
                    sb.append(System.lineSeparator());
                    sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_OXYGEN, a.getOxygen()));
                    sb.append(System.lineSeparator());
                    if (a.getBag().getItems().size() == 0) {
                        sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,
                                "none"));
                        sb.append(System.lineSeparator());
                    } else {
                        sb.append(String.format(ConstantMessages.REPORT_ASTRONAUT_BAG_ITEMS,
                                String.join(", ", a.getBag().getItems())));
                        sb.append(System.lineSeparator());
                    }

                });

        return sb.toString().replaceAll("[\\r\\n]$","");
    }
}
