package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public abstract class BaseAquarium implements Aquarium{

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;

    protected BaseAquarium(String name, int capacity) {
        setName(name);
        setCapacity(capacity);
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    protected void setName(String name) {

        if (name== null || name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }


    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Collection<Decoration> getDecorations() {
        return decorations;
    }

    @Override
    public Collection<Fish> getFish() {
        return fish;
    }

    @Override
    public int calculateComfort() {
        return decorations.stream().mapToInt(d -> d.getComfort()).sum();
    }

    @Override
    public void addFish(Fish fish) {
        if (this.fish.size() == capacity) {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }

        String fishType = fish.getClass().getSimpleName().replaceAll("Fish","");

        if (!this.getClass().getSimpleName().contains(fishType)) {
            throw new IllegalStateException(ConstantMessages.WATER_NOT_SUITABLE);
        }

        this.fish.add(fish);
    }

    @Override
    public void removeFish(Fish fish) {
        this.fish.remove(fish);
    }

    @Override
    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
    }

    @Override
    public void feed() {
        this.fish.forEach(f -> f.eat());
    }

    @Override
    public String getInfo() {

        StringBuilder sb = new StringBuilder();

        String aquaStr = String.format("%s (%s):%n",name, this.getClass().getSimpleName());
        sb.append(aquaStr);
        sb.append("Fish: ");
        if (fish == null || fish.isEmpty()) {
            sb.append("none");
        } else {
            sb.append(fish.stream().map(Fish::getName).collect(Collectors.joining(" ")));
        }
        sb.append(System.lineSeparator());

        sb.append(String.format("Decorations: %d%n",decorations.size()));
        sb.append(String.format("Comfort: %d",calculateComfort()));

        return sb.toString();
    }
}
