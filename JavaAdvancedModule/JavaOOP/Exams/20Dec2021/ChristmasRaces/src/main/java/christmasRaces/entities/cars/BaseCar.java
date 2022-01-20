package christmasRaces.entities.cars;

import christmasRaces.common.ExceptionMessages;

public abstract class BaseCar implements Car {
    private String model;
    // •	model - String
    private int horsePower;
    //•	horsePower - int

    private double cubicCentimeters;
    //•	cubicCentimeters - double
    //o	Every type of car has different cubic centimeters.


    protected BaseCar(String model, int horsePower, double cubicCentimeters) {
        setModel(model);
        setHorsePower(horsePower);
        setCubicCentimeters(cubicCentimeters);
    }

    @Override
    public String getModel() {
        return model;
    }

    protected void setModel(String model) {
        //o	If the model is null, whitespace, or less than 4 symbols,
        // throw an IllegalArgumentException with a message "Model {model} cannot be less than 4 symbols."

        if (model == null || model.trim().isEmpty()
                || model.length() < 4) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_MODEL,
                    model, 4));
        }
        this.model = model;
    }

    @Override
    public int getHorsePower() {
        return horsePower;
    }

    protected void setHorsePower(int horsePower) {
        //o	Every type of car has a different range of valid horsepower.
        // If the horsepower is not in the valid range, throw an IllegalArgumentException with the message
        // "Invalid horse power: {horsepower}.".
        // to be thrown in children ...
        this.horsePower = horsePower;
    }

    @Override
    public double getCubicCentimeters() {
        return cubicCentimeters;
    }

    protected void setCubicCentimeters(double cubicCentimeters) {
        this.cubicCentimeters = cubicCentimeters;
    }

    public double calculateRacePoints(int laps) {
        return this.cubicCentimeters / this.horsePower * laps;
    }
}
