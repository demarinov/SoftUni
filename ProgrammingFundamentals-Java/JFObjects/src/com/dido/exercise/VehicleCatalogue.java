package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VehicleCatalogue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Vehicle> autos = new ArrayList<>();

        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("end")) {

            String[] autoData = input.split("\\s+");
            Vehicle auto = new Vehicle(autoData[0], autoData[1]
                    , autoData[2], Integer.parseInt(autoData[3]));
            autos.add(auto);

            input = sc.nextLine();
        }

        input = sc.nextLine();

        while(!input.equalsIgnoreCase("close the catalogue")) {

            String model = input;

            printAuto(autos,model);

            input = sc.nextLine();
        }

        double sumCars = 0.0d;
        double sumTrucks = 0.0d;
        int countCars = 0;
        int countTrucks = 0;

        for (int i = 0; i < autos.size(); i++) {
            Vehicle auto = autos.get(i);
            if (auto.type.equalsIgnoreCase("car")) {
                sumCars += auto.getHorsePower();
                countCars++;
            } else if (auto.type.equalsIgnoreCase("truck")) {
                sumTrucks += auto.getHorsePower();
                countTrucks++;
            }
        }

        if (countCars > 0) {
            System.out.printf("Cars have average horsepower of: %.2f.%n",(1.0 * sumCars/countCars));
        } else {
            System.out.printf("Cars have average horsepower of: %.2f.%n",0.0d);
        }

        if (countTrucks > 0) {
            System.out.printf("Trucks have average horsepower of: %.2f.",(1.0 * sumTrucks / countTrucks));
        } else {
            System.out.printf("Trucks have average horsepower of: %.2f.",0.0d);
        }

    }

    public static void printAuto(List<Vehicle> autos, String model) {

        for (int i = 0; i < autos.size(); i++) {
            Vehicle auto = autos.get(i);
            if (auto.model.equalsIgnoreCase(model)) {
                System.out.println(auto);
            }
        }
    }
}

class Vehicle {

    String type;
    String model;
    String color;
    Integer horsePower;

    public Vehicle(String type, String model, String color, Integer horsePower) {

        this.type = type;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {

        return "" +
                "Type: " +type.substring(0,1).toUpperCase()
                + type.substring(1) +
                "\nModel: " + model +
                "\nColor: " + color +
                "\nHorsepower: " + horsePower;
    }
}
