package com.dido.exercise;

import java.text.DecimalFormat;
import java.util.Scanner;

public class VehiclesExtended {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Vehicle {initial fuel quantity} {liters per km} {tank capacity}
        String[] carData = sc.nextLine().split("\\s");
        Vehicle car = new Car(Double.parseDouble(carData[1]), Double.parseDouble(carData[2]),
                Double.parseDouble(carData[3]));
        String[] truckData = sc.nextLine().split("\\s");
        Vehicle truck = new Truck(Double.parseDouble(truckData[1]), Double.parseDouble(truckData[2]),
                Double.parseDouble(truckData[3]));
        String[] busData = sc.nextLine().split("\\s");
        Vehicle bus = new Bus(Double.parseDouble(busData[1]), Double.parseDouble(busData[2]),
                Double.parseDouble(busData[3]));

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String[] commandData = sc.nextLine().split("\\s");

            switch (commandData[0]) {

                case "Drive":
                    String type = commandData[1];
                    Double distance = Double.parseDouble(commandData[2]);
                    boolean result = false;
                    if ("Car".equals(type)) {
                        result = car.drive(distance);
                    } else if ("Truck".equals(type)) {
                        result = truck.drive(distance);
                    } else if ("Bus".equals(type)) {
                        result = bus.drive(distance);
                    }

                    if (result) {
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        System.out.printf("%s travelled %s km%n", type, decimalFormat.format(distance));
                    } else {
                        System.out.printf("%s needs refueling%n", type);
                    }

                    break;
                case "DriveEmpty" :
                    type = commandData[1];
                    distance = Double.parseDouble(commandData[2]);
                    result = false;

                    if ("Bus".equals(type)) {
                        result = bus.drive(distance);
                    }

                    if (result) {
                        DecimalFormat decimalFormat = new DecimalFormat("#.##");
                        System.out.printf("%s travelled %s km%n", type, decimalFormat.format(distance));
                    } else {
                        System.out.printf("%s needs refueling%n", type);
                    }

                    break;
                case "Refuel":
                    type = commandData[1];
                    Double liters = Double.parseDouble(commandData[2]);

                    try {
                        if ("Car".equals(type)) {
                            car.refuel(liters);
                        } else if ("Truck".equals(type)) {
                            truck.refuel(liters);
                        } else if ("Bus".equals(type)) {
                            bus.refuel(liters);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getLocalizedMessage());
                    }

                    break;
            }
        }

        System.out.printf("Car: %.2f%n", car.getFuelQuantity());
        System.out.printf("Truck: %.2f%n", truck.getFuelQuantity());
        System.out.printf("Bus: %.2f%n", bus.getFuelQuantity());
    }

    static class Bus extends Vehicle {

        private boolean emptyBus;

        protected Bus(double fuelQuantity, double fuelConsumption, double tankCapacity) {
            super(fuelQuantity, fuelConsumption, tankCapacity);
            setEmptyBus(false);
        }

        @Override
        public boolean drive(double distance) {
            setEmptyBus(false);
            double fuelToConsume = (getFuelConsumption() + 1.4d) * distance;

            if (fuelToConsume <= getFuelQuantity()) {
                setFuelQuantity(getFuelQuantity() - fuelToConsume);
                return true;
            }
            return false;
        }

        public boolean driveEmpty(double distance) {
            setEmptyBus(true);
            double fuelToConsume = getFuelConsumption() * distance;

            if (fuelToConsume <= getFuelQuantity()) {
                setFuelQuantity(getFuelQuantity() - fuelToConsume);
                return true;
            }
            return false;
        }

        @Override
        public void refuel(double liters) {
            super.refuel(liters);
            setFuelQuantity(getFuelQuantity() + liters);
        }

        public boolean isEmptyBus() {
            return emptyBus;
        }

        private void setEmptyBus(boolean emptyBus) {
            this.emptyBus = emptyBus;
        }

    }

    static class Truck extends Vehicle {

        public Truck(double fuelQuantity, double fuelConsumption, double tankCapacity) {
            super(fuelQuantity, fuelConsumption, tankCapacity);
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
            super.refuel(liters * 0.95d);
            setFuelQuantity(getFuelQuantity() + (liters * 0.95d));
        }

        @Override
        protected void setFuelConsumption(double fuelConsumption) {
            super.setFuelConsumption(fuelConsumption + 1.6d);
        }
    }

    static class Car extends Vehicle {

        public Car(double fuelQuantity, double fuelConsumption, double tankCapacity) {
            super(fuelQuantity, fuelConsumption, tankCapacity);
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
            super.refuel(liters);
            setFuelQuantity(getFuelQuantity() + liters);
        }
    }

    static abstract class Vehicle {

        private double fuelQuantity;
        private double fuelConsumption;
        protected double tankCapacity;

        protected Vehicle(double fuelQuantity, double fuelConsumption, double tankCapacity) {
            setFuelQuantity(fuelQuantity);
            setFuelConsumption(fuelConsumption);
            setTankCapacity(tankCapacity);
        }

        public abstract boolean drive(double distance);

        public void refuel(double liters) {
            if (liters <= 0) {
                throw new IllegalArgumentException("Fuel must be a positive number");
            }

            if ((getFuelQuantity() + liters) > getTankCapacity()) {
                throw new IllegalArgumentException("Cannot fit fuel in tank");
            }

        }

        public double getTankCapacity() {
            return tankCapacity;
        }

        private void setTankCapacity(double tankCapacity) {
            this.tankCapacity = tankCapacity;
        }

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
