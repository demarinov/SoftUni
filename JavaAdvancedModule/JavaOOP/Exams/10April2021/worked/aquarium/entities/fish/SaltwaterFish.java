package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish{

    private static final int SIZE = 5;
    private static final String TYPE = "SaltwaterFish";

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, SIZE, price);
        setType(TYPE);
    }

    @Override
    public void eat() {
        super.setSize(super.getSize()+2);
    }
}
