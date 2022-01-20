package com.dido.exercise;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SpeedRacing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        Map<String,Car> carMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String[] carData = sc.nextLine().split("\\s+");
            String model = carData[0];
            Double fuelAmount = Double.parseDouble(carData[1]);
            Double fuelPerKilometer = Double.parseDouble(carData[2]);

            Car car = new Car();
            car.setModel(model);
            car.setFuelAMount(fuelAmount);
            car.setFuelCostPerKilometer(fuelPerKilometer);

            carMap.putIfAbsent(model, car);
        }

        if (!carMap.isEmpty()) {
            String input = sc.nextLine();

            while (!"End".equals(input)) {
                String[] commandData = input.split("\\s+");

                // Drive <CarModel>  <amountOfKm>",
                if ("Drive".equals(commandData[0])) {

                    String model = commandData[1];
                    Double amountOfKm = Double.parseDouble(commandData[2]);

                    if (carMap.containsKey(model)) {
                        Car car = carMap.get(model);
                        car.drive(Math.abs(amountOfKm));
                    }
                }


                input = sc.nextLine();
            }

            carMap.entrySet().stream()
                    .forEach(e -> System.out.printf("%s", e.getValue()));
        }
    }

    static class Car {


        private String model;
        private Double fuelAmount;
        private Double fuelCostPerKilometer;
        private Double distanceTravelled;

        public Car() {
            this.distanceTravelled = 0.0d;
        }

        public void drive(Double amountOfKm) {

            Double maxTravelDistance = fuelAmount / fuelCostPerKilometer;

            if (maxTravelDistance >= amountOfKm) {
                Double fuelToWaste = amountOfKm * fuelCostPerKilometer;
                fuelAmount -= fuelToWaste;
                distanceTravelled += amountOfKm;
            } else {
                System.out.printf("Insufficient fuel for the drive%n");
            }

        }

        public Double getDistanceTravelled() {
            return distanceTravelled;
        }

        public void setDistanceTravelled(Double distanceTravelled) {
            this.distanceTravelled = distanceTravelled;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Double getFuelAmount() {
            return fuelAmount;
        }

        public void setFuelAMount(Double fuelAMount) {
            this.fuelAmount = fuelAMount;
        }

        public Double getFuelCostPerKilometer() {
            return fuelCostPerKilometer;
        }

        public void setFuelCostPerKilometer(Double fuelCostPerKilometer) {
            this.fuelCostPerKilometer = fuelCostPerKilometer;
        }

        @Override
        public String toString() {
            // <Model> <fuelAmount>  <distanceTraveled>",
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            return String.format("%s %.2f %s%n",model,fuelAmount,decimalFormat.format(distanceTravelled));
        }
    }
}
