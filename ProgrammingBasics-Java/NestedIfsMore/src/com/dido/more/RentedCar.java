package com.dido.more;

import java.util.Scanner;

public class RentedCar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        String season = sc.nextLine();
        String category = "";
        double price = 0.0d;
        String type = "";

        if (budget <= 100) {

            category = "Economy class";
//            	Лято – Кабрио – 35% от бюджета
//            	Зима – Джип – 65% от бюджета

            if (season.equals("Summer")) {
                price = 0.35 * budget;
                type = "Cabrio";

            } else if (season.equals("Winter")) {
                price = 0.65 * budget;
                type = "Jeep";
            }

        } else if (budget > 100 && budget <= 500) {
            //            	Лято – Кабрио – 45% от бюджета
            //            	Зима – Джип – 80% от бюджета
            category = "Compact class";

            if (season.equals("Summer")) {
                price = 0.45 * budget;
                type = "Cabrio";
            } else if (season.equals("Winter")) {
                price = 0.80 * budget;
                type = "Jeep";
            }

        } else if (budget > 500) {
            // 90%  от бюджета
            category = "Luxury class";
            price = 0.9 * budget;
            type = "Jeep";
        }

        System.out.println(category);
        System.out.printf("%s - %.2f%n",type,price);
    }
}
