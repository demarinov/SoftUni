package christmasRaces.entities.drivers;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.cars.Car;

public class DriverImpl implements Driver{

    // •	name - String
    private String name;
    //o	If the name is null, empty, or less than 5 symbols
    // throw an IllegalArgumentException with the message "Name {name} cannot be less than 5 symbols.".
    //•	car - Car
    private Car car;
    //•	numberOfWins - int
    private int numberOfWins;
    //•	canParticipate - boolean
    private boolean canParticipate;
    //o	The default behavior is false.
    //o	The Driver can participate in a race, ONLY if he has a Car (Car is not null).


    public DriverImpl(String name) {
        setName(name);
        this.canParticipate = false;
    }

    private void setName(String name) {

        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name, 5));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Car getCar() {
        return this.car;
    }

    @Override
    public int getNumberOfWins() {
        return this.numberOfWins;
    }

    @Override
    public void addCar(Car car) {

        if(car == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_INVALID));
        }

        this.car = car;
        this.canParticipate = true;
    }

    @Override
    public void winRace() {
        this.numberOfWins++;
    }

    @Override
    public boolean getCanParticipate() {
        return this.canParticipate;
    }
}
