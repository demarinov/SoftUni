package com.company.exercise;

import java.util.Scanner;

public class DivNoRemainder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int q1 = 0, q2 = 0, q3 = 0;
        double p1 = 0, p2 = 0, p3 = 0;

        for (int i = 1; i <= n; i++) {
            {

                int num = Integer.parseInt(sc.nextLine());
                if (num % 2 == 0) {
                    q1++;
                    p1 = 100.0 * q1 / n;
                }
                if (num % 3 == 0) {
                    q2++;
                    p2 = 100.0 * q2 / n;
                }

                if (num % 4 == 0) {
                    q3++;

                    p3 = 100.0 * q3 / n;
                }
            }

        }

        System.out.printf("%.2f%%\n",p1);
        System.out.printf("%.2f%%\n",p2);
        System.out.printf("%.2f%%\n",p3);

    }
}
