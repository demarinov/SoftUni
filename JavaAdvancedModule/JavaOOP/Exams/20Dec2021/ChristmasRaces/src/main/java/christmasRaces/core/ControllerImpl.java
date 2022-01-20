package christmasRaces.core;

import christmasRaces.common.ExceptionMessages;
import christmasRaces.common.OutputMessages;
import christmasRaces.core.interfaces.Controller;
import christmasRaces.entities.cars.Car;
import christmasRaces.entities.cars.MuscleCar;
import christmasRaces.entities.cars.SportsCar;
import christmasRaces.entities.drivers.Driver;
import christmasRaces.entities.drivers.DriverImpl;
import christmasRaces.entities.races.Race;
import christmasRaces.entities.races.RaceImpl;
import christmasRaces.repositories.interfaces.Repository;

import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {

    private Repository<Car> carRepository;
    private Repository<Race> raceRepository;
    private Repository<Driver> driverRepository;

    public ControllerImpl(Repository<Driver> driverRepository,
                          Repository<Car> carRepository, Repository<Race> raceRepository) {
        this.carRepository = carRepository;
        this.raceRepository = raceRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public String createDriver(String driver) {

        Driver driverFound = driverRepository.getByName(driver);

        if (driverFound != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_EXISTS,
                    driver));
        }

        driverRepository.add(new DriverImpl(driver));
        return String.format(OutputMessages.DRIVER_CREATED, driver);
    }

    @Override
    public String createCar(String type, String model, int horsePower) {

        Car carFound = carRepository.getByName(model);

        if (carFound != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_EXISTS, model));
        }

        Car car = null;
        String carName;
        switch (type) {
            case "Muscle":
                car = new MuscleCar(model, horsePower);
                carRepository.add(car);
                break;
            case "Sports":
                car = new SportsCar(model, horsePower);
                carRepository.add(car);
                break;
            default:
                break;
        }

        carName = car != null ? car.getClass().getSimpleName(): "";

        return String.format(OutputMessages.CAR_CREATED,carName,model);
    }

    @Override
    public String addCarToDriver(String driverName, String carModel) {

        // Gives the Car with a given name to the Driver with a given name (if exists).
        //If the Driver does not exist in the DriverRepository,
        // throw IllegalArgumentException with message
        //•	"Driver {name} could not be found."
        Driver driverFound = driverRepository.getByName(driverName);
        if (driverFound == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,
                    driverName));
        }

        //If the Car does not exist in the CarRepository, throw IllegalArgumentException with message
        //•	"Car {name} could not be found."
        Car carFound = carRepository.getByName(carModel);
        if (carFound == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAR_NOT_FOUND,
                    carModel));
        }
        //If everything is successful you should add the Car to the
        // Driver and return the following message:
        //•	"Driver {driver name} received car {car name}."

        driverFound.addCar(carFound);
        return String.format(OutputMessages.CAR_ADDED, driverName, carModel);
    }

    @Override
    public String addDriverToRace(String raceName, String driverName) {

        // Adds a Driver to the Race.
        //If the Race does not exist in the RaceRepository,
        // throw an IllegalArgumentException with a message:
        //•	"Race {name} could not be found."
        Race raceFound = raceRepository.getByName(raceName);
        if (raceFound == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,
                    raceName));
        }
        //If the Driver does not exist in the DriverRepository,
        // throw an IllegalArgumentException with a message:
        //•	"Driver {name} could not be found."

        Driver driverFound = driverRepository.getByName(driverName);
        if (driverFound == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.DRIVER_NOT_FOUND,
                    driverName));
        }
        //If everything is successful, you should add the Driver
        // to the Race and return the following message:
        //•	"Driver {driver name} added in {race name} race."

        raceFound.addDriver(driverFound);
        return String.format(OutputMessages.DRIVER_ADDED, driverName, raceName);
    }

    @Override
    public String startRace(String raceName) {

        // If the Race does not exist in RaceRepository,
        // throw an IllegalArgumentException with a message:
        //•	"Race {name} could not be found."
        Race raceFound = raceRepository.getByName(raceName);
        if (raceFound == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_NOT_FOUND,
                    raceName));
        }

        //If the participants in the race are less than 3,
        // throw an IllegalArgumentException with a message:
        //•	"Race {race name} cannot start with less than 3 participants."
        if (raceFound.getDrivers() == null || raceFound.getDrivers().size() < 3) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_INVALID,
                    raceName, 3));
        }

        //If everything is successful, you should return the following message:
        //•	"Driver {first driver name} wins {race name} race."
        //"Driver {second driver name} is second in {race name} race."
        //"Driver {third driver name} is third in {race name} race."

        StringBuilder output = new StringBuilder();

        List<Driver> winners = raceFound.getDrivers().stream().sorted((d1, d2) -> {
            Car car1 = d1.getCar();
            Double car1Points = car1.calculateRacePoints(raceFound.getLaps());

            Car car2 = d2.getCar();
            Double car2Points = car2.calculateRacePoints(raceFound.getLaps());
            return car2Points.compareTo(car1Points);
        }).limit(3).collect(Collectors.toList());

        output.append(String.format(OutputMessages.DRIVER_FIRST_POSITION,winners.get(0).getName(),
                raceName));
        output.append(System.lineSeparator());
        output.append(String.format(OutputMessages.DRIVER_SECOND_POSITION,winners.get(1).getName(),
                raceName));
        output.append(System.lineSeparator());
        output.append(String.format(OutputMessages.DRIVER_THIRD_POSITION,winners.get(2).getName(),
                raceName));

        raceRepository.remove(raceFound);
        return output.toString();
    }

    @Override
    public String createRace(String name, int laps) {

        // Creates a Race with the given name and laps and adds it to the RaceRepository.
        //If the Race with the given name already exists,
        // throw an IllegalArgumentException with a message:
        //•	"Race {name} is already created."
        Race raceFound = raceRepository.getByName(name);
        if (raceFound != null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.RACE_EXISTS,
                    name));
        }
        //If everything is successful you should return the following message:
        //•	"Race {name} is created."

        raceRepository.add(new RaceImpl(name, laps));
        return String.format(OutputMessages.RACE_CREATED, name);
    }
}
