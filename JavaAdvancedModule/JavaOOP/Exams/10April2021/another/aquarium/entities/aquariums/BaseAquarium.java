package aquarium.entities.aquariums;

import aquarium.common.ConstantMessages;
import aquarium.common.ExceptionMessages;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.fish.Fish;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseAquarium implements Aquarium{

    private String name;
    private int capacity;
    private Collection<Decoration> decorations;
    private Collection<Fish> fish;
    private String type;

    protected BaseAquarium(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.decorations = new ArrayList<>();
        this.fish = new ArrayList<>();
    }

    protected void setName(String name) {

        if (name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.AQUARIUM_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }


    protected void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
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
        if (this.fish.size() < capacity) {
            this.fish.add(fish);
        } else {
            throw new IllegalStateException(ConstantMessages.NOT_ENOUGH_CAPACITY);
        }
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

        String aquaStr = String.format("%s (%s):%n",name, type);
        sb.append(aquaStr);
        sb.append("Fish: ");
        if (fish.isEmpty()) {
            sb.append("none%n");
        } else {
            fish.forEach(f -> sb.append(f.getName()+" "));
            sb.append("\n");
        }

        sb.append(String.format("Decorations: %d%n",decorations.size()));
        sb.append(String.format("Comfort: %d",calculateComfort()));

        return sb.toString();
    }
}
