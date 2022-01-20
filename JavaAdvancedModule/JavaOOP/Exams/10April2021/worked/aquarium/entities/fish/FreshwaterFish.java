package aquarium.entities.fish;

public class FreshwaterFish extends BaseFish{

    private static final int SIZE = 3;
    private static final String TYPE = "FreshwaterFish";

    public FreshwaterFish(String name, String species, double price) {
        super(name, species, SIZE, price);
        setType(TYPE);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize()+SIZE);
    }
}
