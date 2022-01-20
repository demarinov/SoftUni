package com.dido.exams0;

import java.util.Scanner;

public class BlackFlag {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int days = Integer.parseInt(sc.nextLine());
        int dailyPlunder = Integer.parseInt(sc.nextLine());

        double expectedPlunder = Double.parseDouble(sc.nextLine());

        double totalPlunder = 0.0d;
        for (int i = 1; i <= days; i++) {

            totalPlunder += dailyPlunder;

            if (i % 3 == 0) {
                // additional plunder
                totalPlunder += (0.5 * dailyPlunder);
            }

            if (i % 5 == 0) {
                // lose plunder
                totalPlunder -= (0.3 * totalPlunder);
            }
        }

        if (totalPlunder >= expectedPlunder) {
            System.out.printf("Ahoy! %.2f plunder gained.",totalPlunder);
        } else {
            double percentPlunder = totalPlunder / expectedPlunder * 100.0;
            System.out.printf("Collected only %.2f%% of the plunder.", percentPlunder);
        }
    }
}
