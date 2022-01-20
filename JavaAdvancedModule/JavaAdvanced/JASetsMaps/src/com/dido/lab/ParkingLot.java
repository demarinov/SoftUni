package com.dido.lab;

import java.sql.SQLOutput;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ParkingLot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program that:
//•	Records car number for every car that enter in the parking lot
//•	Removes car number when the car go out
//        Input
//        The input will be string in format [direction, carNumber]
//        The input ends with string "END"
//        Output
//        Print the output with all car numbers which are in parking lot

        String input = sc.nextLine();
        Set<String> carNumbersSet = new LinkedHashSet<>();

        while (!input.equals("END")) {
            String[] inputData = input.split(", ");

            String command = inputData[0];
            String carNumber = inputData[1];

            switch (command) {
                case "IN":
                    if (!carNumbersSet.contains(carNumber)) {
                        carNumbersSet.add(carNumber);
                    }
                    break;
                case "OUT":
                    if (carNumbersSet.contains(carNumber)) {
                        carNumbersSet.remove(carNumber);
                    }
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        if (!carNumbersSet.isEmpty()) {
            carNumbersSet.stream().forEach(c -> System.out.println(c));

        } else {
            System.out.println("Parking Lot is Empty");
        }
    }
}
