package glacialExpedition.core;

import glacialExpedition.common.ConstantMessages;
import glacialExpedition.common.ExceptionMessages;
import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.Repository;
import glacialExpedition.repositories.StateRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Explorer> explorerRepository;
    private Repository<State> stateRepository;

    private int exploredStatesCount;

    public ControllerImpl() {

        this.explorerRepository = new ExplorerRepository();
        this.stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {

        switch (type) {
            case "NaturalExplorer":
                explorerRepository.add(new NaturalExplorer(explorerName));
                break;
            case "GlacierExplorer":
                explorerRepository.add(new GlacierExplorer(explorerName));
                break;
            case "AnimalExplorer":
                explorerRepository.add(new AnimalExplorer(explorerName));
                break;
            default:
                throw new IllegalArgumentException(ExceptionMessages.EXPLORER_INVALID_TYPE);
        }

        return String.format(ConstantMessages.EXPLORER_ADDED,type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {

        State state = new StateImpl(stateName);
        Arrays.stream(exhibits).forEach(e -> state.getExhibits().add(e));

        stateRepository.add(state);

        return String.format(ConstantMessages.STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {

        Explorer explorer = explorerRepository.byName(explorerName);

        if (explorer == null) {
             throw new
                     IllegalArgumentException(String.format(ExceptionMessages.EXPLORER_DOES_NOT_EXIST,
                     explorerName));
        }

        explorerRepository.remove(explorer);

        return String.format(ConstantMessages.EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {
        Mission mission = new MissionImpl();

        Collection<Explorer> suitableExplorers = explorerRepository.getCollection()
                .stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());

        if (suitableExplorers.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.STATE_EXPLORERS_DOES_NOT_EXISTS);
        }

        State state = stateRepository.byName(stateName);
        mission.explore(state, suitableExplorers);
        exploredStatesCount++;

        long retiredExplorers = suitableExplorers.stream().filter(e -> !e.canSearch()).count();
//        stateRepository.remove(state);
        return String.format(ConstantMessages.STATE_EXPLORER,
                stateName, retiredExplorers); // retiredExplorers
    }

    @Override
    public String finalResult() {

        // "{exploredStatesCount} states were explored."
        //"Information for the explorers:"
        //"Name: {explorerName}"
        //"Energy: {explorerName}"
        //"Suitcase exhibits: {suitcaseExhibits1, suitcaseExhibits2,
        // suitcaseExhibits3, …, suitcaseExhibits n}"
        StringBuilder output = new StringBuilder();
        output.append(String.format(ConstantMessages.FINAL_STATE_EXPLORED, exploredStatesCount));
        output.append(System.lineSeparator());
        output.append(ConstantMessages.FINAL_EXPLORER_INFO);
        explorerRepository.getCollection().stream()
                .forEach(e -> {
                    output.append(System.lineSeparator());
                    output.append(String.format(ConstantMessages.FINAL_EXPLORER_NAME,e.getName()));
                    output.append(System.lineSeparator());
                    output.append(String.format(ConstantMessages.FINAL_EXPLORER_ENERGY,e.getEnergy()));
                    output.append(System.lineSeparator());

                    if (e.getSuitcase().getExhibits().size() == 0) {
                        output.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS, "None"));
                    } else {
                        String joinedExhibits = e.getSuitcase().
                                getExhibits().
                                stream().collect(Collectors.joining(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER));
                        output.append(String.format(ConstantMessages.FINAL_EXPLORER_SUITCASE_EXHIBITS,joinedExhibits));
                    }
                });

        return output.toString();
    }
}
