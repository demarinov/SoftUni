package com.dido.exercise;

import java.util.Scanner;

public class NeedForSpeed {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Car car = new Car(10,97);
        car.drive(2);
        System.out.println(car.getFuel());
    }
}

class CrossMotorcycle extends Motorcycle {
    public CrossMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
    }
}

class RaceMotorcycle extends Motorcycle {
    private static final double DEFAULT_FUEL_CONSUMPTION = 8d;
    public RaceMotorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}

class FamilyCar extends Car {
    public FamilyCar(double fuel, int horsePower) {
        super(fuel, horsePower);
    }
}

class SportCar extends Car{
    private static final double DEFAULT_FUEL_CONSUMPTION = 10d;
    public SportCar(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}

class Car extends Vehicle {
    private static final double DEFAULT_FUEL_CONSUMPTION = 3d;
    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        super.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(double fuel, int horsePower) {
        super(fuel, horsePower);
    }
}

class Vehicle {
    private static final double DEFAULT_FUEL_CONSUMPTION = 1.25d;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

    public Vehicle(double fuel, int horsePower) {
        this.fuel = fuel;
        this.horsePower = horsePower;
        this.fuelConsumption = DEFAULT_FUEL_CONSUMPTION;
    }

    public void drive(double kilometers) {
        double fuelConsumed = kilometers * fuelConsumption;

        if (fuelConsumed <= fuel) {
            fuel -= fuelConsumed;
        }
    }

    public static double getDefaultFuelConsumption() {
        return DEFAULT_FUEL_CONSUMPTION;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
