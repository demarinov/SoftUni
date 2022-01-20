package com.dido.more;

import java.util.Scanner;

public class Vacation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        String season = sc.nextLine();
        String type = "";
        double price = 0.0d;
        String place = "";

        if (budget <= 1000) {

            type = "Camp";
//            	Лято – Аляска – 65% от бюджета
            //	Зима – Мароко – 45% от бюджета
            if (season.equals("Summer")) {
                place = "Alaska";
                price = 0.65 * budget;
            } else if (season.equals("Winter")) {
                place = "Morocco";
                price = 0.45 * budget;
            }

        } else if (budget > 1000 && budget <= 3000) {

            type ="Hut";
//            	Лято – Аляска – 80% от бюджета
            //	Зима – Мароко – 60% от бюджета
            if (season.equals("Summer")) {
                place = "Alaska";
                price = 0.80 * budget;
            } else if (season.equals("Winter")) {
                place = "Morocco";
                price = 0.60 * budget;
            }

        } else if (budget > 3000) {
            type="Hotel";
            // 90%
//            	Лято – Аляска
            //	Зима – Мароко

            if (season.equals("Summer")) {
                place = "Alaska";
                price = 0.90 * budget;
            } else if (season.equals("Winter")) {
                place = "Morocco";
                price = 0.90 * budget;
            }


        }

        System.out.printf("%s - %s - %.2f%n",place,type,price);
    }
}
