package spaceStation.models.mission;

import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {

    public MissionImpl() {
    }

    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {


        if (astronauts.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

       for (Astronaut astronaut : astronauts) {

            for (int j = 0; j < planet.getItems().size(); j++) {
                String item = planet.getItems().toArray(new String[0])[j];
                astronaut.getBag().getItems().add(item);
                planet.getItems().remove(item);
                j--;
                astronaut.breath();

                if (!astronaut.canBreath()) {
                    break;
                }
            }

//            while(astronaut.canBreath()) {
//
//                if (planet.getItems().isEmpty()) {
//                    return;
//                }
//
//                astronaut.breath();
//                String item = planet.getItems().remove(0);
//                astronaut.getBag().getItems().add(item);
//            }

        }
    }

}
