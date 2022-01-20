package com.dido.more;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RawData {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<PowerCar> cars = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] carData = sc.nextLine().split("\\s+");

            // “<Model> <EngineSpeed> <EnginePower> <CargoWeight> <CargoType>
            // <Tire1Pressure> <Tire1Age> <Tire2Pressure> <Tire2Age>
            // <Tire3Pressure> <Tire3Age> <Tire4Pressure> <Tire4Age>”

            List<Tyre> tyres = new ArrayList<>();
            tyres.add(new Tyre(carData[5], carData[6]));
            tyres.add(new Tyre(carData[7], carData[8]));
            tyres.add(new Tyre(carData[9], carData[10]));
            tyres.add(new Tyre(carData[11], carData[12]));

            PowerEngine engine = new PowerEngine(carData[1], carData[2]);
            Cargo cargo = new Cargo(carData[3], carData[4]);
            PowerCar car = new PowerCar(carData[0], engine,cargo, tyres);

            cars.add(car);
        }

        String command = sc.nextLine();

        if (command.equalsIgnoreCase("fragile")) {
            printFragileCars(cars, command);
        } else if (command.equalsIgnoreCase("flamable")) {
            printFlammableCars(cars, command);
        }
    }

    public static void printFlammableCars(List<PowerCar> cars, String command) {

        for (int i = 0; i < cars.size(); i++) {
            PowerCar car = cars.get(i);

            boolean hasHighEnginePower = checkForHighEnginePower(car);
            if (car.getCargo().getType().equalsIgnoreCase(command) && hasHighEnginePower) {
                System.out.println(car.getModel());
            }
        }
    }

    public static boolean checkForHighEnginePower(PowerCar car) {

        PowerEngine engine = car.getEngine();

        if (Integer.parseInt(engine.getPower()) > 250) {

            return true;
        }

        return false;

    }

    public static void printFragileCars(List<PowerCar> cars, String command) {

        for (int i = 0; i < cars.size(); i++) {
            PowerCar car = cars.get(i);

            boolean hasLowPressureTyre = checkForLowPressureTyre(car);
            if (car.getCargo().getType().equalsIgnoreCase(command) && hasLowPressureTyre) {
                System.out.println(car.getModel());
            }
        }
    }



    public static boolean checkForLowPressureTyre(PowerCar car) {

        List<Tyre> tyres = car.getTyres();
        for (int i = 0; i < tyres.size(); i++) {

            Tyre tyre = tyres.get(i);
            if (Double.parseDouble(tyre.getPressure()) < 1) {
                return true;
            }
        }

        return false;
    }
}

class Cargo {

    private String weight;
    private String type;

    public Cargo(String weight, String type) {
        this.weight = weight;
        this.type = type;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "weight='" + weight + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

class PowerEngine {
     private String speed;
     private String power;

     public PowerEngine(String speed, String power) {
         this.speed = speed;
         this.power = power;
     }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "PowerEngine{" +
                "speed='" + speed + '\'' +
                ", power='" + power + '\'' +
                '}';
    }
}

class Tyre {

    private String pressure;
    private String age;

    public Tyre(String pressure, String age) {
        this.pressure = pressure;
        this.age = age;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "pressure='" + pressure + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

class PowerCar {

    private String model;
    private PowerEngine engine;
    private Cargo cargo;
    private List<Tyre> tyres;

    public PowerCar(String model, PowerEngine engine, Cargo cargo, List<Tyre> tyres) {
        this.model = model;
        this.engine = engine;
        this.cargo = cargo;
        this.tyres = tyres;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public PowerEngine getEngine() {
        return engine;
    }

    public void setEngine(PowerEngine engine) {
        this.engine = engine;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    @Override
    public String toString() {
        return "PowerCar{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", cargo=" + cargo +
                ", tyres=" + tyres +
                '}';
    }
}
