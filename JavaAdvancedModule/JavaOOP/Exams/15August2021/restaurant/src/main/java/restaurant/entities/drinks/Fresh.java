package restaurant.entities.drinks;

public class Fresh extends BaseBeverage{

    private static final double PRICE = 3.50d;

    public Fresh(String name, int counter, String brand) {
        super(name, counter, PRICE, brand);
    }
}
