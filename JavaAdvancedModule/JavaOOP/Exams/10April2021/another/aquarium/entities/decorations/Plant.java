package aquarium.entities.decorations;

public class Plant extends BaseDecoration{

    private static final int DEFAULT_COMFORT = 5;
    private static final double DEFAULT_PRICE = 10;
    private static final String TYPE = "Plant";

    public Plant() {
        super(DEFAULT_COMFORT, DEFAULT_PRICE);
        setType(TYPE);
    }
}
