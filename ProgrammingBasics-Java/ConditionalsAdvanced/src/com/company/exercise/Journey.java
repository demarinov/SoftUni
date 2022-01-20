package com.company.exercise;

import java.util.Scanner;

public class Journey {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        String season = sc.nextLine();

        String tripDest = "";
        double tripPrice = 0.0d;
        String tripType = "";

        if (budget <= 100.0d) {
            tripDest = "Bulgaria";

            if (season.equals("summer")) {
                // 30% spent
                tripPrice = 0.3d * budget;
                tripType = "Camp";
            } else if (season.equals("winter")) {
                // 70% spent
                tripPrice = 0.7d * budget;
                tripType = "Hotel";
            }

        } else if (budget <= 1000) {
            tripDest = "Balkans";

            if (season.equals("summer")) {
                // 40% spent
                tripPrice = 0.4d * budget;
                tripType = "Camp";

            } else if (season.equals("winter")) {
                // 80% spent
                tripPrice = 0.8d * budget;
                tripType = "Hotel";

            }

        } else if (budget > 1000) {
            tripDest = "Europe";
            tripType = "Hotel";
            // 90% spent
            tripPrice = budget * 0.9d;
        }

        System.out.println("Somewhere in "+tripDest);
        System.out.printf("%s - %.2f",tripType,tripPrice);
    }
}
