package com.dido.exercise;

import java.util.Scanner;

public class SpiceMustFlow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int startYield = n;
        int yield = startYield;
        int totalSpice = 0;
        int days = 0;

        while(yield >= 100) {

            int dailySpice = yield - 26;

            totalSpice += dailySpice;
            yield -= 10;
            days++;
        }

        if (totalSpice >= 26) {
            totalSpice -= 26;
        }

        System.out.println(days);
        System.out.println(totalSpice);
    }
}
