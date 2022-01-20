package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut{

    private static final double OXYGEN = 70;

    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        // •	The method decreases the astronauts' oxygen by 5 units.

        this.setOxygen(this.getOxygen() - 5);
    }

}
