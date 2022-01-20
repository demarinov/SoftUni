package com.company.more;

import java.util.Scanner;

public class HappyCatParking {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int days = Integer.parseInt(sc.nextLine());
        int hours = Integer.parseInt(sc.nextLine());
        double totalAmount = 0.0d;

        for (int i = 1; i <= days; i++) {
            double dailyAmount = 0.0d;
            for (int j = 1; j <= hours; j++) {
                double amount = 0;
                if (i % 2 == 0 && j % 2 != 0) {
                    amount = 2.5d;
                } else if (i % 2 != 0 && j % 2 == 0) {
                    amount = 1.25d;
                } else {
                    amount = 1.0d;
                }

                dailyAmount += amount;
            }

            System.out.printf("Day: %d - %.2f leva%n",i,dailyAmount);
            totalAmount += dailyAmount;
        }

        System.out.printf("Total: %.2f leva",totalAmount);

    }
}
