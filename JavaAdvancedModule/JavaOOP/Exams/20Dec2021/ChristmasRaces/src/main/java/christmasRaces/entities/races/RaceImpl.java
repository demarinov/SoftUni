package christmasRaces.entities.races;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

public class RaceImpl implements Race {

    // •	name - String
    private String name;
    //o	If the name is null, empty, or less than 5 symbols
    // throw an IllegalArgumentException with the message "Name {name} cannot be less than 5 symbols.".
    //•	laps - int
    private int laps;
    //•	drivers - A Collection of Drivers
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        setName(name);
        setLaps(laps);
        this.drivers = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {

        if (name == null || name.trim().isEmpty() || name.length() < 5) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NAME, name));
        }
        this.name = name;
    }

    @Override
    public int getLaps() {
        return this.laps;
    }

    private void setLaps(int laps) {
        //o	Throws IllegalArgumentException with message
        // "Laps cannot be less than 1.", if the laps are less than 1.

        if (laps < 1) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.INVALID_NUMBER_OF_LAPS,
                    1));
        }

        this.laps = laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return this.drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        // •	If a Driver is null throw an IllegalArgumentException with a message
        // "Driver cannot be null.".
        //•	If a Driver cannot participate in the Race
        // (the Driver doesn't own a Car) throw an IllegalArgumentException
        // with a message "Driver {driver name} could not participate in race.".
        //•	If the Driver already exists in the Race throw an IllegalArgumentException with a message:
        //"Driver {driver name} is already added in {race name} race.".

        if (driver == null) {
            throw new IllegalArgumentException(ExceptionMessages.DRIVER_INVALID);
        }

        if (driver.getCar() == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_PARTICIPATE,
                    driver.getName()));
        }

        Driver driverFound = this.getDrivers().stream().
                filter(d -> d.getName().equals(driver.getName())).findFirst().orElse(null);
        if (driverFound != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_ALREADY_ADDED,
                    driver.getName(), this.getName()));
        }

        this.drivers.add(driver);
    }
}
