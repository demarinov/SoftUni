package com.dido.lab.judge.carenhanced;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            String[] carData = sc.nextLine().split("\\s");

            String brand = carData[0];
            Car car;
            if (carData.length > 1) {
                String model = carData[1];
                Integer horsePower = Integer.parseInt(carData[2]);
                car = new Car(brand, model, horsePower);
            } else {
                car = new Car(brand);
            }

            System.out.println(car);

        }
    }

    static class Car {

        private String brand;
        private String model;
        private Integer horsePower;

        public Car(String brand) {
            this.brand = brand;
            this.model = "unknown";
            this.horsePower = -1;
        }

        public Car(String brand, String model, Integer horsePower) {
            this.brand = brand;
            this.model = model;
            this.horsePower = horsePower;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Integer getHorsePower() {
            return horsePower;
        }

        public void setHorsePower(Integer horsePower) {
            this.horsePower = horsePower;
        }

        @Override
        public String toString() {
            return String.format("The car is: %s %s - %d HP.",brand,model,horsePower);
        }
    }
}
