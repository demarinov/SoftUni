package com.dido.exercise.judge.carsaleman;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        List<Engine> enginesList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            // engines
            // "<Model> <Power> <Displacement> <Efficiency>".
            String[] engineData = sc.nextLine().split("\\s+");
            String model = engineData[0];
            Integer power = Integer.parseInt(engineData[1]);


            Engine engine = new Engine(model, power);

            String displacement = "";
            String eff = "";
            if (engineData.length == 4) {
                displacement = engineData[2];
                eff = engineData[3];

                engine.setDisplacement(displacement);
                engine.setEfficiency(eff);
            }

            if (engineData.length == 3) {

                // disp or eff ???
                if (isDigitsOnly(engineData[2])) {
                    // disp
                    engine.setDisplacement(engineData[2]);
                } else {
                    // eff
                    engine.setEfficiency(engineData[2]);
                }

            }
            enginesList.add(engine);
        }

        Integer m = Integer.parseInt(sc.nextLine());

        List<Car> carList = new LinkedList<>();
        for (int i = 0; i < m; i++) {

            // cars
            String[] carData = sc.nextLine().split("\\s+");
            // "<Model> <Engine> <Weight> <Color>",

            String model = carData[0];
            String engineModel = carData[1];


            Engine engine = findEngine(enginesList, engineModel);

            Car car;
            if (engine != null) {
                car = new Car(model, engine);

                String weight = "";
                String color = "";
                if (carData.length == 4) {
                    weight = carData[2];
                    color = carData[3];
                    car.setWeight(weight);
                    car.setColor(color);
                }

                if (carData.length == 3) {

                    if (isDigitsOnly(carData[2])) {
                        // weight
                        car.setWeight(carData[2]);
                    } else {
                        // color
                        car.setColor(carData[2]);
                    }
                }

                carList.add(car);
            }


        }

        carList.stream()
                .forEach(System.out::println);
    }

    public static Engine findEngine(List<Engine> engineList, String model) {

        Engine engine = engineList.stream().filter(e -> model.equals(e.getModel()))
                .findFirst().orElse(null);

        return engine;
    }

    public static boolean isDigitsOnly(String str) {

        for (int i = 0; i < str.length(); i++) {

            Character c = str.charAt(i);

            if (Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    // Define two classes Car and Engine. A Car has a model, engine, weight and color.
    static class Car {
        private String model;
        private Engine engine;
        private String weight; // optional
        private String color; // optional


        public Car(){}

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
            // <CarModel>:
            //<EngineModel>:
            //Power: <EnginePower>
            //Displacement: <EngineDisplacement>
            //Efficiency: <EngineEfficiency>
            //Weight: <CarWeight>
            //Color: <CarColor>
            return String.format("%s:%n" +
                    "%s%nWeight: %s%n" +
                    "Color: %s",getModel(), getEngine(),getWeight(), getColor());
        }
    }
    // An Engine has model, power, displacement and efficiency.
    static class Engine {
        private String model;
        private Integer power;
        private String displacement; // optional
        private String efficiency; // optional

        public Engine(String model, Integer power) {

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

        public Integer getPower() {
            return power;
        }

        public void setPower(Integer power) {
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
            // <EngineModel>:
            //Power: <EnginePower>
            //Displacement: <EngineDisplacement>
            //Efficiency: <EngineEfficiency>
            return String.format("%s:%n" +
                    "Power: %d%n" +
                    "Displacement: %s%n" +
                    "Efficiency: %s",getModel(), getPower(), getDisplacement(), getEfficiency());
        }
    }
    // A Car's weight and color and its Engine's displacements and efficiency are optional.


}
