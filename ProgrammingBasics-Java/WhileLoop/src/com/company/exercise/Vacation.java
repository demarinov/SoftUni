package com.company.exercise;

import java.util.Scanner;

public class Vacation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double holidayCash = Double.parseDouble(sc.nextLine());
        double cashNow = Double.parseDouble(sc.nextLine());
        String action = "";
        double amount = 0.0d;
        int countSpendDays = 0;
        int days = 0;

        while (countSpendDays < 5 && cashNow < holidayCash) {

            action = sc.nextLine();
            amount = Double.parseDouble(sc.nextLine());
            days++;

            if (action.equals("spend")) {
                cashNow -= amount;
                countSpendDays++;
                if (cashNow < 0 ) {
                    cashNow = 0;
                }
            } else if (action.equals("save")) {
                cashNow += amount;
                countSpendDays = 0;
            }

        }

        if (countSpendDays == 5) {
            System.out.println("You can't save the money.");
            System.out.println(days);
        }
        if (cashNow >= holidayCash) {
            System.out.println("You saved the money for " +
                    days +
                    " days.");
        }

    }
}
