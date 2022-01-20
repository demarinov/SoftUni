package core;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.BaseAquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import repositories.DecorationRepository;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

public class ControllerImpl implements Controller{

    private DecorationRepository decorations;
    private Collection<Aquarium> aquariums;

    public ControllerImpl() {
        this.decorations = new DecorationRepository();
        this.aquariums = new ArrayList<>();
    }

    public DecorationRepository getDecorationRepository() {
        return decorations;
    }

    public Collection<Aquarium> getAquariums() {
        return Collections.unmodifiableCollection(aquariums);
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        if (!aquariumType.equals("FreshwaterAquarium") && !aquariumType.equals("SaltwaterAquarium")) {

            throw new NullPointerException(ExceptionMessages.INVALID_AQUARIUM_TYPE);
        }

        Aquarium aquarium;
        if (aquariumType.equals("SaltwaterAquarium")) {
            aquarium = new SaltwaterAquarium(aquariumName);
        } else {
            aquarium = new FreshwaterAquarium(aquariumName);
        }
        aquariums.add(aquarium);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_AQUARIUM_TYPE,aquariumType);
    }

    @Override
    public String addDecoration(String type) {

        if (!type.equals("Ornament") && !type.equals("Plant")) {

            throw new IllegalArgumentException(ExceptionMessages.INVALID_DECORATION_TYPE);
        }


        Decoration decoration;

        if (type.equals("Ornament")) {
            decoration = new Ornament();
        } else {
            decoration = new Plant();
        }

        decorations.add(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_TYPE,type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {

        Decoration decoration = decorations.findByType(decorationType);

        if (decoration == null) {
            throw new IllegalArgumentException(String.
                    format(ExceptionMessages.NO_DECORATION_FOUND,decorationType));
        }

        Aquarium aquarium = getAquariumByName(aquariumName);
        // names are always valid
        aquarium.addDecoration(decoration);

        decorations.remove(decoration);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM,
                decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {

        if (!fishType.equals("FreshwaterFish") && !fishType.equals("SaltwaterFish")) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FISH_TYPE);
        }

        Aquarium aquarium = getAquariumByName(aquariumName);
        BaseAquarium baseAquarium = (BaseAquarium) aquarium;
        Fish fish = null;
        if ((baseAquarium.getType().contains("Freshwater") && !fishType.contains("Freshwater"))
                || (baseAquarium.getType().contains("Saltwater") && !fishType.contains("Saltwater"))) {

            return ConstantMessages.WATER_NOT_SUITABLE;
        } else if (baseAquarium.getType().contains("Freshwater") && fishType.contains("Freshwater")) {
            fish = new FreshwaterFish(fishName, fishSpecies, price);
        } else {
            fish = new SaltwaterFish(fishName, fishSpecies, price);
        }

        aquarium.addFish(fish);

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM,
                fishType, aquariumName);
    }

    private Aquarium getAquariumByName(String aquariumName) {
        Aquarium aquarium = aquariums.stream().filter(a -> a.getName().equals(aquariumName))
                .findFirst().orElse(null);
        return aquarium;
    }

    @Override
    public String feedFish(String aquariumName) {

        Aquarium aquarium = getAquariumByName(aquariumName);
        AtomicReference<Integer> countFedFish = new AtomicReference<>(0);
        aquarium.getFish().forEach(f -> {
            f.eat();
            countFedFish.getAndSet(countFedFish.get() + 1);
        });
        return String.format(ConstantMessages.FISH_FED, countFedFish.get());
    }

    @Override
    public String calculateValue(String aquariumName) {

        Aquarium aquarium = getAquariumByName(aquariumName);

        Double fishTotalPriceInAquarium = aquarium.getFish().stream().mapToDouble(f -> f.getPrice())
                .sum();
        Double decorationTotalPriceInAquarium = aquarium.getDecorations().stream().
                mapToDouble(d -> d.getPrice()).sum();

        Double aquariumValue = fishTotalPriceInAquarium + decorationTotalPriceInAquarium;

        return String.format(ConstantMessages.VALUE_AQUARIUM, aquariumName, aquariumValue);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        aquariums.forEach(a -> {
            sb.append(a.getInfo());
            sb.append(System.lineSeparator());
        });
        return sb.substring(0, sb.length()-1);
    }
}
