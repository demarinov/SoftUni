package com.dido.more;

import java.util.Scanner;

public class IntervalsGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int rounds = Integer.parseInt(sc.nextLine());
        double pointsTotal = 0.0d;
        int i1 =0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;

        for (int i = 1; i <= rounds; i++) {

            int points = Integer.parseInt(sc.nextLine());

           //            •	От 0 до 9  20 % от числото
           //•	От 10 до 19  30 % от числото
           //•	От 20 до 29  40 % от числото
           //•	От 30 до 39  50 точки
           //•	От 40 до 50  100 точки
           //•	Невалидно число  резултата се дели на 2


            if (points >= 0 && points <= 9) {
                pointsTotal += (0.20 * points);
                i1++;
            } else if (points >= 10 && points <= 19) {
                pointsTotal += (0.30 * points);
                i2++;
            } else if (points >= 20 && points <= 29) {
                pointsTotal += (0.40 * points);
                i3++;
            } else if (points >= 30 && points <= 39) {
                pointsTotal += (50.0);
                i4++;
            } else if (points >= 40 && points <= 50) {
                pointsTotal += (100.0);
                i5++;
            } else {
                pointsTotal /= 2;
                i6++;
            }
        }

        System.out.printf("%.2f%n",pointsTotal);
        System.out.printf("From 0 to 9: %.2f%%%n", (100.0 * i1/rounds));
        System.out.printf("From 10 to 19: %.2f%%%n", (100.0 * i2/rounds));
        System.out.printf("From 20 to 29: %.2f%%%n", (100.0 * i3/rounds));
        System.out.printf("From 30 to 39: %.2f%%%n", (100.0 * i4/rounds));
        System.out.printf("From 40 to 50: %.2f%%%n", (100.0 * i5/rounds));
        System.out.printf("Invalid numbers: %.2f%%%n", (100.0 * i6/rounds));


    }
}
