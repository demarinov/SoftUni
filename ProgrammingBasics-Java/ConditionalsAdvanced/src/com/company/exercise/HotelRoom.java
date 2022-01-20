package com.company.exercise;

import java.util.Scanner;

public class HotelRoom {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String month = sc.nextLine();
        int nights = Integer.parseInt(sc.nextLine());
        double studioPrice = 0.0d;
        double apPrice = 0.0d;

        if (month.equals("May") || month.equals("October")) {
            // price st: 50, ap: 65
            studioPrice = nights * 50.0d;
            apPrice = nights * 65.0d;

            if (nights > 14) {
                // disc: 30 %
                studioPrice = studioPrice * (1 - 0.3d);
            } else if (nights > 7) {
                // disc 5%
                studioPrice = studioPrice * (1 - 0.05d);
            }

        } else if (month.equals("June") || month.equals("September")) {
            // prices st: 75.20, ap: 68.7
            studioPrice = nights * 75.2d;
            apPrice = nights * 68.7d;

            if (nights > 14) {
                // disc: 20%
                studioPrice = studioPrice * (1 - 0.2d);
            }

        } else if (month.equals("July") || month.equals("August")) {
            // prices st: 76, ap: 77
            studioPrice = nights * 76.0d;
            apPrice = nights * 77.0d;
        }

        // ap
        if (nights > 14) {
            // ap only
            // disc: 10%
            apPrice = apPrice * (1 - 0.1d);
        }

        System.out.printf("Apartment: %.2f lv.\n",apPrice);
        System.out.printf("Studio: %.2f lv.",studioPrice);

    }
}
