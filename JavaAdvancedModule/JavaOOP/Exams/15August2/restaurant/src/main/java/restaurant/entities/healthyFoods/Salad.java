package restaurant.entities.healthyFoods;

public class Salad extends Food{

    private static final double InitialSaladPortion = 150d;

    public Salad(String name,  double price) {
        super(name, InitialSaladPortion, price);
    }
}
