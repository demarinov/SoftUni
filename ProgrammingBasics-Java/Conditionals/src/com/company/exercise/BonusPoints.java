package com.company.exercise;

import java.util.Scanner;

public class BonusPoints {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double bonus = 0.0d;

        if (n > 1000) {
            bonus = 0.1d * n;
        } else if (n <= 100) {
            bonus = 5.0d;
        } else if (n > 100) {
            bonus = 0.2d * n;
        }


        if (n % 2 == 0) {
            bonus = bonus + 1;
        } else if (n % 5 == 0) {
            bonus = bonus + 2;
        }

        System.out.printf("%.1f\n", bonus);
        System.out.printf("%.1f", (n + bonus));
    }
}
