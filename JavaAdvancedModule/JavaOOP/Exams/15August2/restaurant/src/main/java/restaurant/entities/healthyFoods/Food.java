package restaurant.entities.healthyFoods;

import restaurant.common.ExceptionMessages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;

public abstract class Food implements HealthyFood {

    // •	name - String

    //•	portion - double

    //•	price - double

    private String name;
    private double portion;
    private double price;

    protected Food(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
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
    public double getPortion() {
        return portion;
    }

    protected void setPortion(double portion) {
        //o	If the portion is less or equal to 0,
        // throw an IllegalArgumentException with message "Portion cannot be less or equal to zero!"
        if (portion <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
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
}
