package com.dido.more;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarSalesman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Engine> engines = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] engineData = sc.nextLine().split("\\s+");

            Engine engine = new Engine(engineData[0], Double.parseDouble(engineData[1]));

            if (engineData.length >= 3) {
                if (digitsOnly(engineData[2])) {
                    engine.setDisplacement(engineData[2]);
                } else {
                    engine.setEfficiency(engineData[2]);
                }
            }

            if (engineData.length >= 4) {
                if (digitsOnly(engineData[3])) {
                    engine.setDisplacement(engineData[3]);
                } else {
                    engine.setEfficiency(engineData[3]);
                }
            }

            engines.add(engine);
        }


        int m = Integer.parseInt(sc.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] carData = sc.nextLine().split("\\s+");

            String engineModel = carData[1];
            Engine engine = findEngine(engines, engineModel);
            Car car = new Car(carData[0], engine);

            if (carData.length >= 3) {
                if (digitsOnly(carData[2])) {
                    car.setWeight(carData[2]);
                } else {
                    car.setColor(carData[2]);
                }
            }

            if (carData.length >= 4) {
                if (!digitsOnly(carData[3])) {
                    car.setColor(carData[3]);
                } else {
                    car.setWeight(carData[3]);
                }
            }

            cars.add(car);
        }

        printCars(cars);

    }

    public static boolean digitsOnly(String str) {

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            switch(c) {

                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    break;
                default:
                    return false;
            }
        }

        return true;
    }

    public static void printCars(List<Car> cars) {

        for (int i = 0; i < cars.size(); i++) {

//            <CarModel>:
//            <EngineModel>:
//            Power: <EnginePower>
//            Displacement: <EngineDisplacement>
//            Efficiency: <EngineEfficiency>
//            Weight: <CarWeight>
//            Color: <CarColor>
            Car car = cars.get(i);
            Engine engine = car.getEngine();

            System.out.println(car.getModel() + ":");

            if (engine != null) {
                System.out.println(engine.getModel() + ":");
                System.out.println("Power: " + new DecimalFormat("0.#").format(engine.getPower()));

                System.out.println("Displacement: " + engine.getDisplacement());
                System.out.println("Efficiency: " + engine.getEfficiency());


            }

            System.out.println("Weight: " + car.getWeight());

            System.out.println("Color: " + car.getColor());
        }
    }

    public static Engine findEngine(List<Engine> engines, String model) {

        for (int i = 0; i < engines.size(); i++) {
            Engine engine = engines.get(i);

            if (engine.getModel().equalsIgnoreCase(model)) {

                return engine;
            }
        }

        return null;
    }
}
//    Define two classes Car and Engine. A Car has a model, engine, weight and color.
//        An Engine has model, power, displacement and efficiency.
//        A Car’s weight and color and its Engine’s displacements and efficiency are optional.

class Engine {
    String model;
    Double power;
    String displacement;
    String efficiency;

    public Engine(String model, Double power) {
        this.model = model;
        this.power = power;
        this.displacement = "n/a";
        this.efficiency = "n/a";

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public String getDisplacement() {
        return displacement;
    }

    public void setDisplacement(String displacement) {
        this.displacement = displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "model='" + model + '\'' +
                ", power=" + power +
                ", displacement=" + displacement +
                ", efficiency='" + efficiency + '\'' +
                '}';
    }
}

class Car {

    String model;
    Engine engine;
    String weight;
    String color;

    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
        this.weight = "n/a";
        this.color = "n/a";
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", engine=" + engine +
                ", weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
