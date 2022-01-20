package com.dido.exercise.judge.rawdata;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        List<Car> carsList = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            String[] carData = sc.nextLine().split("\\s+");
            String model = carData[0];
            Integer engineSpeed = Integer.parseInt(carData[1]);
            Integer enginePower = Integer.parseInt(carData[2]);
            Engine engine = new Engine(engineSpeed, enginePower);
            Integer cargoWeight = Integer.parseInt(carData[3]);
            String cargoType = carData[4];
            Cargo cargo = new Cargo(cargoType, cargoWeight);

            // <Model> <EngineSpeed> <EnginePower> <CargoWeight> <CargoType>
            // <Tire1Pressure>
            // <Tire1Age> <Tire2Pressure>
            // <Tire2Age> <Tire3Pressure> <Tire3Age> <Tire4Pressure> <Tire4Age>"

            List<Tire> tires = new LinkedList<>();
            Double tirePressure = Double.parseDouble(carData[5]);
            Integer tireAge = Integer.parseInt(carData[6]);
            tires.add(new Tire(tireAge, tirePressure));

            tirePressure = Double.parseDouble(carData[7]);
            tireAge = Integer.parseInt(carData[8]);
            tires.add(new Tire(tireAge, tirePressure));

            tirePressure = Double.parseDouble(carData[9]);
            tireAge = Integer.parseInt(carData[10]);
            tires.add(new Tire(tireAge, tirePressure));

            tirePressure = Double.parseDouble(carData[11]);
            tireAge = Integer.parseInt(carData[12]);
            tires.add(new Tire(tireAge, tirePressure));

            Car car = new Car(model, engine, cargo, tires);
            carsList.add(car);
        }

        // After the N lines you will receive a single line with one of 2 commands
        // "fragile" or "flamable" ,
        // The cars should be printed in order of appearing in the input.

        String command = sc.nextLine();

        if ("fragile".equals(command)) {

            // if the command is "fragile" print all cars
            // whose Cargo Type is "fragile" with a tire whose pressure is  < 1,
            carsList.stream()
                    .filter(c -> {
                        Cargo cargo = c.getCargo();
                        String type = cargo.getType();

                        Tire tire = c.getTiresList().stream().filter(t -> t.getPressure() < 1)
                                .findAny().orElse(null);

                        return command.equals(type) && tire != null;
                    })
                    .forEach(c -> System.out.println(c.getModel()));
        } else if ("flamable".equals(command)) {

            // if the command is "flamable" print all cars whose Cargo Type is "flamable"
            // and have Engine Power > 250.

            carsList.stream()
                    .filter(c -> {
                        Cargo cargo = c.getCargo();
                        String type = cargo.getType();
                        Integer enginePower = c.getEngine().getPower();

                        return command.equals(type) && enginePower > 250;
                    })
                    .forEach(System.out::println);
        }
    }

    static class Car {

        private String model;
        private Engine engine;
        private Cargo cargo;
        // max 4
        private List<Tire> tiresList;

        public Car(String model, Engine engine, Cargo cargo, List<Tire> tires) {

            this.model = model;
            this.engine = engine;
            this.cargo = cargo;
            this.tiresList = tires;
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

        public Cargo getCargo() {
            return cargo;
        }

        public void setCargo(Cargo cargo) {
            this.cargo = cargo;
        }

        public List<Tire> getTiresList() {
            return tiresList;
        }

        public void setTiresList(List<Tire> tiresList) {
            this.tiresList = tiresList;
        }

        @Override
        public String toString() {
            return this.getModel();
        }
    }

    static class Tire {

        private Integer age;
        private Double pressure;

        public Tire() {

        }

        public Tire(Integer age, Double pressure) {
            this.age = age;
            this.pressure = pressure;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Double getPressure() {
            return pressure;
        }

        public void setPressure(Double pressure) {
            this.pressure = pressure;
        }
    }

    static class Cargo {

        private String type;
        private Integer weight;

        public Cargo() {

        }

        public Cargo(String type, Integer weight) {
            this.type = type;
            this.weight = weight;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }
    }

    static class Engine {
        private Integer speed;
        private Integer power;

        public Engine() {

        }

        public Engine(Integer speed, Integer power) {
            this.speed = speed;
            this.power = power;
        }

        public Integer getSpeed() {
            return speed;
        }

        public void setSpeed(Integer speed) {
            this.speed = speed;
        }

        public Integer getPower() {
            return power;
        }

        public void setPower(Integer power) {
            this.power = power;
        }
    }
}
