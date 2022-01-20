package restaurant.entities.drinks;

public class Smoothie extends BaseBeverage{

    private static final double smoothiePrice = 4.50d;

    public Smoothie(String name, int counter, String brand) {
        super(name, counter, smoothiePrice, brand);
    }


}
