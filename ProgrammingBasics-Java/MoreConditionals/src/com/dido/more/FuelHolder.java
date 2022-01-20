package com.dido.more;

import java.util.Scanner;

public class FuelHolder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fuelType = sc.nextLine();
        double liters = Double.parseDouble(sc.nextLine());
        boolean invalidFuel = false;

        if (!fuelType.equals("Diesel") && !fuelType.equals("Gasoline")
                && !fuelType.equals("Gas")) {
            invalidFuel = true;
        }

        if (liters >= 25 && !invalidFuel) {
            System.out.printf("You have enough %s.",fuelType.toLowerCase());
        } else if (liters < 25 && !invalidFuel) {
            System.out.printf("Fill your tank with %s!",fuelType.toLowerCase());
        } else if (invalidFuel) {
            System.out.println("Invalid fuel!");
        }
    }
}
