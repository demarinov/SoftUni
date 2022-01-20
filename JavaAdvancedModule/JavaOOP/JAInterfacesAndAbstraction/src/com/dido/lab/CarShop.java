package com.dido.lab;

import java.io.Serializable;

public class CarShop {

    public static void main(String[] args) {

        Car seat = new Seat("Leon","gray",110,"Spain");

        System.out.println(String.format("%s is %s color and have %d horse power",
                seat.getModel(),seat.getColor(), seat.getHorsePower()));
        System.out.println(seat);
    }

    interface Car {

        Integer TIRES = 4;
        String getModel();
        String getColor();
        Integer getHorsePower();
        String countryProduced();
    }

    static class Seat implements Car, Serializable {

        private String model;
        private String color;
        private Integer horsePower;
        private String countryProduced;

        public Seat(String model, String color, Integer horsePower, String countryProduced) {
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

        private Integer getTires() {
            return Car.TIRES;
        }

        @Override
        public String toString() {
            return String.format("This is %s produced in %s and have %d tires",
                    getModel(), countryProduced(),getTires());
        }
    }
}
