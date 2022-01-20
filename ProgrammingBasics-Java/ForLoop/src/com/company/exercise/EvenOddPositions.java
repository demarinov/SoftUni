package com.company.exercise;

import java.lang.management.MonitorInfo;
import java.util.Scanner;

public class EvenOddPositions {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double sumEven = 0, sumOdd = 0;
        double maxEven = -1000000000.0d;
        double minEven = Double.MAX_VALUE;
        double maxOdd = -1000000000.0d;
        double minOdd = Double.MAX_VALUE;



        // calc sum, min, max for even and odd nums
        for (int i = 1; i <= n; i++) {

            double num = Double.parseDouble(sc.nextLine());

            if (i % 2 == 0) {
                sumEven += num;

                if (maxEven < num) {
                    maxEven = num;
                }

                if (minEven > num) {
                    minEven = num;
                }
            } else {

                sumOdd += num;

                if (maxOdd < num) {
                    maxOdd = num;
                }

                if (minOdd > num) {
                    minOdd = num;
                }
            }

        }


        System.out.printf("OddSum=%.2f,\n",sumOdd);
        if (minOdd != Double.MAX_VALUE) {
            System.out.printf("OddMin=%.2f,\n", minOdd);
        } else {
            System.out.printf("OddMin=%s,\n", "No");
        }
        if (maxOdd != -1000000000.0d) {
            System.out.printf("OddMax=%.2f,\n", maxOdd);
        } else {
            System.out.printf("OddMax=%s,\n", "No");
        }

        System.out.printf("EvenSum=%.2f,\n",sumEven);

        if (minEven != Double.MAX_VALUE) {
            System.out.printf("EvenMin=%.2f,\n",minEven);
        } else {
            System.out.printf("EvenMin=%s,\n", "No");
        }
        if (maxEven != -1000000000.0d) {
            System.out.printf("EvenMax=%.2f\n",maxEven);
        } else {
            System.out.printf("EvenMax=%s\n", "No");
        }

    }
}
