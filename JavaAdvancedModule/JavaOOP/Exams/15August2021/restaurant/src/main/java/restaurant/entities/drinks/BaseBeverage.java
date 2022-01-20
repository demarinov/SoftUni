package restaurant.entities.drinks;

import restaurant.common.ExceptionMessages;
import restaurant.entities.drinks.interfaces.Beverages;


public abstract class BaseBeverage implements Beverages {

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

    protected void setName(String name) {

        if (name ==null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }

        this.name = name;
    }

    protected void setCounter(int counter) {
        if (counter <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COUNTER);
        }
        this.counter = counter;
    }

    protected void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    protected void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCounter() {
        return this.counter;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }
}
