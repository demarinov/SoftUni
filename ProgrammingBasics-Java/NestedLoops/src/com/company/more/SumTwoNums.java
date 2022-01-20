package com.company.more;

import java.util.Scanner;

public class SumTwoNums {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        int m1 = Integer.parseInt(sc.nextLine());
        boolean foundSum = false;
        int combo = 0;
        int nu1 = 0;
        int nu2 = 0;

        for (int i = n1; i <= n2; i++) {
            for (int j = n1; j <= n2; j++) {

                int sum = i + j;
                combo++;
                if (sum == m1) {
                    foundSum = true;
                    nu1 = i;
                    nu2 = j;
                    break;
                }
            }

            if (foundSum) {
                break;
            }
        }

        if (foundSum) {
            System.out.println("Combination " +
                    "N:" +
                    combo + " (" +
                    nu1 + " + " + nu2 +
                    " = " +
                    m1 +
                    ")");
        } else {
            System.out.println(combo + " " +
                    "combinations - neither equals " +
                    m1);
        }

    }
}
