package restaurant.entities.drinks;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;

public abstract class BaseBeverage implements Beverages {

    // •	name - String

    //•	counter - int

    //•	price - double

    //•	brand - String


    private String name;
    private int counter;
    private double price;
    private String brand;

    protected BaseBeverage(String name, int counter, double price, String brand) {
        setName(name);
        setCounter(counter);
        setPrice(price);
        setBrand(brand);
    }

    @Override
    public String getName() {
        return name;
    }

    protected void setName(String name) {
        //o	If the name is null or whitespace,
        // throw an IllegalArgumentException with message "Name cannot be null or white space!"

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    protected void setCounter(int counter) {
        //o	If the counter is less or equal to 0,
        // throw an IllegalArgumentException with message "Counter cannot be less or equal to zero!"

        if (counter <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COUNTER);
        }
        this.counter = counter;
    }

    @Override
    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        //o	If the price is less or equal to 0,
        // throw an IllegalArgumentException with message "Price cannot be less or equal to zero!"
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }

        this.price = price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    protected void setBrand(String brand) {
        //o	If the brand is null or whitespace,
        // throw an IllegalArgumentException with message "Brand cannot be null or white space!"
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }
}
