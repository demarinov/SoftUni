package com.dido.more;

import java.util.Scanner;

public class MatchTickets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //        •	VIP – 499.99 лева.
        //•	Normal – 249.99 лева.

        //        •	От 1 до 4 – 75% от бюджета.
        //•	От 5 до 9 – 60% от бюджета.
        //•	От 10 до 24 – 50% от бюджета.
        //•	От 25 до 49 – 40% от бюджета.
        //•	50 или повече – 25% от бюджета.

        double budget = Double.parseDouble(sc.nextLine());
        String category = sc.nextLine();
        int people = Integer.parseInt(sc.nextLine());
        double remBudget = budget;

        if (people >= 1 && people <= 4) {
            remBudget -= (0.75 * budget);
        } else if (people >= 5 && people <= 9) {
            remBudget -= (0.60 * budget);
        } else if (people >= 10 && people <= 24) {
            remBudget -= (0.50 * budget);
        } else if (people >= 25 && people <= 49) {
            remBudget -= (0.40 * budget);
        } else if (people >= 50) {
            remBudget -= (0.25 * budget);
        }

        double diff = 0.0d;
        double cost = 0.0d;

        if (category.equals("VIP")) {
            cost = 499.99 * people;
            if (remBudget >= cost) {
                diff = remBudget - cost;
                System.out.printf("Yes! You have %.2f leva left.",diff);
            } else {
                diff = cost - remBudget;
                System.out.printf("Not enough money! You need %.2f leva.",diff);
            }
        } else if (category.equals("Normal")) {
            cost = 249.99 * people;
            if (remBudget >= cost) {

                diff = remBudget - cost;
                System.out.printf("Yes! You have %.2f leva left.",diff);
            } else {
                diff = cost - remBudget;
                System.out.printf("Not enough money! You need %.2f leva.",diff);
            }
        }


    }
}
