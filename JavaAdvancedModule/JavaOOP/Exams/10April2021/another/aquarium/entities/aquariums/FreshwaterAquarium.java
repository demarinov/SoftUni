package aquarium.entities.aquariums;

public class FreshwaterAquarium extends BaseAquarium{
    private static final int CAPACITY = 50;
    private static final String TYPE = "FreshwaterAquarium";

    public FreshwaterAquarium(String name) {
        super(name, CAPACITY);
        setType(TYPE);
    }


}
