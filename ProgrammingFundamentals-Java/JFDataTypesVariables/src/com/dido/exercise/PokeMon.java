package com.dido.exercise;

import java.util.Scanner;

public class PokeMon {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int y = Integer.parseInt(sc.nextLine());
        int countPoks = 0;
        int power = n;

        while (power >= m) {

            power -= m;
            countPoks++;

            if (power == (0.50 * n)) {

                if (power >= y && y > 0) {
                    power /= y;
                }
            }
        }

        System.out.println(power);
        System.out.println(countPoks);

    }
}
