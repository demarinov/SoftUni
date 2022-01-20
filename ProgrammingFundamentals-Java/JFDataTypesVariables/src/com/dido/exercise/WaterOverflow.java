package com.dido.exercise;

import java.util.Scanner;

public class WaterOverflow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int vol = 0;

        for (int i = 1; i <= n; i++) {

            int liters = Integer.parseInt(sc.nextLine());
            if (vol + liters > 255) {
                System.out.printf("Insufficient capacity!%n");
                continue;
            }
            vol += liters;
            // 255 liters tank
        }

        System.out.printf("%d",vol);
    }
}
