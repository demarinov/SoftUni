package com.dido.lab;

import java.util.Scanner;

public class FerrariMain {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String driverName = sc.nextLine();

        Car ferrari = new Ferrari(driverName);

        System.out.println(ferrari);

    }

    static class Ferrari implements Car {

        private static final String MODEL = "488-Spider";
        private String driverName;

        public Ferrari(String driverName) {
            setDriverName(driverName);
        }

        @Override
        public String brakes() {
            return Car.super.brakes();
        }

        @Override
        public String gas() {
            return "brum-brum-brum-brrrrr";
        }

        private void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        @Override
        public String toString() {
            return String.format("%s/%s/%s/%s",MODEL, this.brakes(), this.gas(),this.driverName);
        }
    }

    interface Car {

        default String brakes() {
            return "Brakes!";
        }

        default String gas(){
            return "Zadu6avam sA!";
        }
    }
}
