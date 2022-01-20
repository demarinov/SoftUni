package com.dido.exams6;

import java.util.Scanner;

// Cooking Masterclass
public class Problem1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        int students = Integer.parseInt(sc.nextLine());
        double flourPrice = Double.parseDouble(sc.nextLine());
        double eggPrice = Double.parseDouble(sc.nextLine());
        double apronPrice = Double.parseDouble(sc.nextLine());


        // set per student 1 flour, 10 eggs, 1 apron
        double totalCost = 0.0d;

        // student == packages
        int freePackages = students / 5;

        double apronCost = apronPrice * Math.ceil(students + 0.2 * students);
        double flourCost = flourPrice * (students - freePackages);
        double eggsCost = eggPrice * students * 10;

        totalCost = apronCost + flourCost + eggsCost;

        if (totalCost <= budget) {
            System.out.printf("Items purchased for %.2f$.",totalCost);
        } else {
            System.out.printf("%.2f$ more needed.",(totalCost - budget));
        }
    }
}
