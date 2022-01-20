package com.dido.lab;

import java.io.Serializable;

public class CarShopExtended {

    public static void main(String[] args) {

        Sellable seat = new Seat("Leon", "Gray", 110,
                "Spain", 11111.1);
        Rentable audi = new Audi("A4", "Gray", 110,
                "Germany", 3, 99.9);

        printCarInfo((Car) seat);
        printCarInfo((Car) audi);

    }

    private static void printCarInfo(Car car) {
        System.out.println(String.format(
                "%s is %s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()));
        System.out.println(car);
    }


    interface Sellable{

        Double getPrice();
    }

    interface Rentable {

        Integer getMinRentDay();
        Double getPricePerDay();
    }

    interface Car {

        Integer TIRES = 4;
        String getModel();
        String getColor();
        Integer getHorsePower();
        String countryProduced();
    }



    static class Audi extends CarImpl implements Rentable {

        private Integer minRateDay;
        private Double pricePerDay;

        public Audi(String model, String color, Integer horsePower,
                    String countryProduced, Integer minRateDay, Double pricePerDay) {
            super(model, color, horsePower, countryProduced);
            setMinRateDay(minRateDay);
            setPricePerDay(pricePerDay);
        }

        private void setMinRateDay(Integer minRateDay) {
            this.minRateDay = minRateDay;
        }

        private void setPricePerDay(Double pricePerDay) {
            this.pricePerDay = pricePerDay;
        }

        @Override
        public Integer getMinRentDay() {
            return minRateDay;
        }

        @Override
        public Double getPricePerDay() {
            return pricePerDay;
        }

        @Override
        public String toString() {
            return String.format("Minimum rental period of %d days. Price per day %f",
                    getMinRentDay(), getPricePerDay());
        }
    }

    static class CarImpl implements Car {
        private String model;
        private String color;
        private Integer horsePower;
        private String countryProduced;

        public CarImpl(String model, String color, Integer horsePower, String countryProduced) {
            setModel(model);
            setColor(color);
            setHorsePower(horsePower);
            setCountryProduced(countryProduced);

        }

        private void setModel(String model) {
            this.model = model;
        }

        private void setColor(String color) {
            this.color = color;
        }

        private void setHorsePower(Integer horsePower) {
            this.horsePower = horsePower;
        }

        private void setCountryProduced(String countryProduced) {
            this.countryProduced = countryProduced;
        }

        @Override
        public String getModel() {
            return this.model;
        }

        @Override
        public String getColor() {
            return this.color;
        }

        @Override
        public Integer getHorsePower() {
            return this.horsePower;
        }

        @Override
        public String countryProduced() {
            return this.countryProduced;
        }



    }

    static class Seat extends CarImpl implements Sellable,Serializable {

        private Double price;

        public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
            super(model, color, horsePower, countryProduced);
            setPrice(price);
        }

        private Integer getTires() {
            return Car.TIRES;
        }

        @Override
        public String toString() {
            return String.format("This is %s produced in %s and have %d tires%n" +
                            "Leon sells for %f",
                    getModel(), countryProduced(),getTires(),getPrice());
        }

        private void setPrice(Double price) {
            this.price = price;
        }

        @Override
        public Double getPrice() {
            return this.price;
        }
    }
}
