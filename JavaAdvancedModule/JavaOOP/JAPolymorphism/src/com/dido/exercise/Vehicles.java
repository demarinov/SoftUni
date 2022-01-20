package com.dido.exercise;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Vehicles {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] carData = sc.nextLine().split("\\s");
        Vehicle car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]));
        String[] truckData = sc.nextLine().split("\\s");
        Vehicle truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]));

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] commandData = sc.nextLine().split("\\s");

            switch(commandData[0]) {

                case "Drive":
                    String type = commandData[1];
                    Double distance = Double.parseDouble(commandData[2]);
                    boolean result = false;
                    if ("Car".equals(type)) {
                        result = car.drive(distance);
                    } else if ("Truck".equals(type)) {
                        result = truck.drive(distance);
                    }

                    if (result) {
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        System.out.printf("%s travelled %s km%n",type,decimalFormat.format(distance));
                    } else {
                        System.out.printf("%s needs refueling%n", type);
                    }

                    break;
                case "Refuel":
                    type = commandData[1];
                    Double liters = Double.parseDouble(commandData[2]);

                    if ("Car".equals(type)) {
                        car.refuel(liters);
                    } else if ("Truck".equals(type)) {
                        truck.refuel(liters);
                    }

                    break;
            }
        }

        System.out.printf("Car: %.2f%n",car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n",truck.getFuelQuantity());
    }

    static class Truck extends Vehicle {

        public Truck(double fuelQuantity, double fuelConsumption) {
            super(fuelQuantity, fuelConsumption);
        }

        @Override
        public boolean drive(double distance) {
            double fuelToConsume = getFuelConsumption() * distance;


            if (fuelToConsume <= getFuelQuantity()) {
                setFuelQuantity(getFuelQuantity() - fuelToConsume);
                return true;
            }
            return false;
        }

        @Override
        public void refuel(double liters) {
            setFuelQuantity(getFuelQuantity() + (liters * 0.95d));
        }

        @Override
        protected void setFuelConsumption(double fuelConsumption) {
            super.setFuelConsumption(fuelConsumption + 1.6d);
        }
    }

    static class Car extends Vehicle {

        public Car(double fuelQuantity, double fuelConsumption) {
            super(fuelQuantity, fuelConsumption);
        }

        @Override
        public boolean drive(double distance) {
            double fuelToConsume = getFuelConsumption() * distance;


            if (fuelToConsume <= getFuelQuantity()) {
                setFuelQuantity(getFuelQuantity() - fuelToConsume);
                return true;
            }

            return false;
        }

        @Override
        protected void setFuelConsumption(double fuelConsumption) {
            super.setFuelConsumption(fuelConsumption + 0.9d);
        }

        @Override
        public void refuel(double liters) {
            setFuelQuantity(getFuelQuantity() + liters);
        }
    }

    static abstract class Vehicle {

        private double fuelQuantity;
        private double fuelConsumption;

        protected Vehicle(double fuelQuantity, double fuelConsumption) {
            setFuelQuantity(fuelQuantity);
            setFuelConsumption(fuelConsumption);
        }

        public abstract boolean drive(double distance) ;
        public abstract void refuel(double liters);

        public double getFuelQuantity() {
            return fuelQuantity;
        }

        protected void setFuelQuantity(double fuelQuantity) {
            this.fuelQuantity = fuelQuantity;
        }

        public double getFuelConsumption() {
            return fuelConsumption;
        }

        protected void setFuelConsumption(double fuelConsumption) {
            this.fuelConsumption = fuelConsumption;
        }
    }
}
