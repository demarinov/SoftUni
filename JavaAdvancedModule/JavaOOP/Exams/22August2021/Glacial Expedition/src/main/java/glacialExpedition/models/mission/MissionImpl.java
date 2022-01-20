package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MissionImpl implements Mission {


    @Override
    public void explore(State state, Collection<Explorer> explorers) {

        for (Explorer explorer : explorers) {

            //• Explorers cannot go on expeditions if their energy is below 0.
            //•	They leave the station and start collecting exhibits one by one.
            //•	If they find an exhibit, their energy is decreased.
            //•	They add the exhibit to their carton. The exhibit should then be removed from the state.
            //•	Explorers cannot continue collecting exhibits if their energy drops to 0.
            //o	If their energy drops to 0, the next explorer starts exploring.

            while (explorer.canSearch() && !state.getExhibits().isEmpty()) {


                //•	They add the exhibit to their carton. The exhibit should then be removed from the state.
                List<String> stateExhibits = state.getExhibits().stream().collect(Collectors.toList());
                explorer.getSuitcase().getExhibits().add(stateExhibits.get(0));
                state.getExhibits().remove(stateExhibits.get(0));

                explorer.search();
            }

        }


    }
}
