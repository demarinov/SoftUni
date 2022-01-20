package com.dido.lab;

import java.util.Scanner;

public class CarInfo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n =Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {

            // {brand}  {model} {horsePower}
            Car car = new Car();
            String[] carData = sc.nextLine().split("\\s");
            car.setBrand(carData[0]);
            car.setModel(carData[1]);
            car.setHorsePower(Integer.parseInt(carData[2]));


            System.out.println(car);

        }


    }

    static class Car {

        private String brand;
        private String model;
        private Integer horsePower;

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
            return String.format("The car is: %s %s – %d HP.",brand,model,horsePower);
        }
    }
}

