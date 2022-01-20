package com.dido.more;

import java.util.Scanner;

public class CenterPoint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double x1 = Double.parseDouble(sc.nextLine());
        double y1 = Double.parseDouble(sc.nextLine());
        double x2 = Double.parseDouble(sc.nextLine());
        double y2 = Double.parseDouble(sc.nextLine());

        printClosestToCenterPoint(x1,y1,x2,y2);

    }

    public static void printClosestToCenterPoint(double x1, double y1, double x2, double y2) {

        double d1 = Math.sqrt((x1 * x1 + y1 * y1));
        double d2 = Math.sqrt((x2 * x2 + y2 * y2));

        if (d1 <= d2) {
            System.out.printf("(%.0f, %.0f)",x1,y1);
        } else {
            System.out.printf("(%.0f, %.0f)",x2,y2);
        }
    }
}
