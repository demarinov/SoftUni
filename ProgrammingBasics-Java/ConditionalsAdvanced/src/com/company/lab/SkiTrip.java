package com.company.lab;

import java.util.Scanner;

public class SkiTrip {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = Integer.parseInt(sc.nextLine());
        String roomType = sc.nextLine();
        String hotelEvaluation = sc.nextLine();
        int nights = days - 1;
        double totalPrice = 0.0d;

         // prices -> 18, 25, 35
        if (roomType.equals("room for one person")) {
            totalPrice = nights * 18.0d;


        } else if (roomType.equals("apartment")) {
            totalPrice = nights * 25.0d;

            // discount -> 30%, 35%, 50%
            if (days < 10) {
                totalPrice = totalPrice * (1 - 0.30d);
            } else if (days >=10 && days <= 15) {
                totalPrice = totalPrice * (1 - 0.35d);
            } else if (days > 15) {
                totalPrice = totalPrice * (1 - 0.50d);
            }
        } else if (roomType.equals("president apartment")) {
            totalPrice = nights * 35.0d;

            // discount -> 10%, 15%, 20%
            if (days < 10) {
                totalPrice = totalPrice * (1 - 0.10d);
            } else if (days >=10 && days <= 15) {
                totalPrice = totalPrice * (1 - 0.15d);
            } else if (days > 15) {
                totalPrice = totalPrice * (1 - 0.20d);
            }
        }

        if (hotelEvaluation.equals("positive")) {
            totalPrice = totalPrice * (1 + 0.25d);
        } else if (hotelEvaluation.equals("negative")) {
            totalPrice = totalPrice * (1 - 0.10d);
        }

        System.out.printf("%.2f",totalPrice);

    }
}
