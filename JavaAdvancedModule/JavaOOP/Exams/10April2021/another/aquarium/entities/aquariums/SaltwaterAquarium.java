package aquarium.entities.aquariums;

public class SaltwaterAquarium extends BaseAquarium{

    private static final int CAPACITY = 25;
    private static final String TYPE = "SaltwaterAquarium";

    public SaltwaterAquarium(String name) {
        super(name, CAPACITY);
        setType(TYPE);
    }
}
