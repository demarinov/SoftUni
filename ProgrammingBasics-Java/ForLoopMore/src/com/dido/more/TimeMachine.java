package com.dido.more;

import java.util.Scanner;

public class TimeMachine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double inheritedMoney = Double.parseDouble(sc.nextLine());
        int finalLifeYear = Integer.parseInt(sc.nextLine());
        double inheritedMoneyLeft = inheritedMoney;

        for (int i = 1800; i <= finalLifeYear; i++) {

            if (i % 2 == 0) {
                inheritedMoneyLeft -= 12000;
            } else {
                int yearsDelta = i - 1800;
                inheritedMoneyLeft -= (12000 + 50 *(18+yearsDelta));
            }

        }

        if (inheritedMoneyLeft < 0) {
            System.out.printf("He will need %.2f dollars to survive.",Math.abs(inheritedMoneyLeft));
        } else {
            System.out.printf("Yes! He will live a carefree life and will have %.2f dollars left.",inheritedMoneyLeft);
        }
    }
}
