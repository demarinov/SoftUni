package com.dido.main;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Vehicle car = new Car();
    }

    static class Car extends Vehicle {

        public Car() {
            super();
        }

        @Override
        public void drive() {
            System.out.println("Drive from Car");
        }
    }

    static class Vehicle {

        protected Vehicle() {
            drive();
        }

        public void drive() {
            System.out.println("Drive from vehicle");
        }
    }
}
