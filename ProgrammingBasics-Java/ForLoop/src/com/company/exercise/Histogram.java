package com.company.exercise;

import java.util.Scanner;

public class Histogram {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int r1 = 0, r2 = 0, r3 = 0, r4 = 0, r5 = 0;
        double p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;

        for (int i = 1; i <= n; i++) {

            int num = Integer.parseInt(sc.nextLine());

            if (num < 200) {
                r1++;
                p1 = 100.0 * r1/n;
            } else if (num >= 200 && num <= 399) {
                r2++;
                p2 = 100.0 * r2/n;
            } else if (num >= 400 && num <= 599) {
                r3++;
                p3 = 100.0 * r3/n;
            } else if (num >= 600 && num <= 799) {
                r4++;
                p4 = 100.0 * r4/n;
            } else if (num >= 800 && num <= 1000) {
                r5++;
                p5 = 100.0 * r5/n;
            }

        }

        System.out.printf("%.2f%%\n",p1);
        System.out.printf("%.2f%%\n",p2);
        System.out.printf("%.2f%%\n",p3);
        System.out.printf("%.2f%%\n",p4);
        System.out.printf("%.2f%%\n",p5);
    }
}
