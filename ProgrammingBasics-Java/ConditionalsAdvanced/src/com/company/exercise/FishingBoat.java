package com.company.exercise;

import java.util.Scanner;

public class FishingBoat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int budget = Integer.parseInt(sc.nextLine());
        String season = sc.nextLine();
        int qty = Integer.parseInt(sc.nextLine());

        double tripPrice = 0.0d;

        if (season.equals("Spring")) {
            // price 3000
            tripPrice = 3000.0d;
        } else if (season.equals("Summer") || season.equals("Autumn")) {
            // price 4200
            tripPrice = 4200.0d;
        } else if (season.equals("Winter")) {
            // price 2600
            tripPrice = 2600.0d;
        }

        if (qty <= 6) {
            tripPrice = tripPrice * (1 - 0.1d);
        } else if (qty >= 7 && qty<=11) {
            tripPrice = tripPrice * (1 - 0.15d);
        } else if (qty >=12) {
            tripPrice = tripPrice * (1 - 0.25d);
        }

        if (!season.equals("Autumn") && qty % 2 == 0) {
            tripPrice = tripPrice * (1 - 0.05d);
        }

        double budgetDiff = budget - tripPrice;

        if (budgetDiff >= 0) {
            System.out.printf("Yes! You have %.2f leva left.",budgetDiff);
        } else {
            System.out.printf("Not enough money! You need %.2f leva.", Math.abs(budgetDiff));
        }
    }
}
