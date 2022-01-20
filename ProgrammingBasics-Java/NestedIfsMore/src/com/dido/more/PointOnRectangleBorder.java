package com.dido.more;

import java.util.Scanner;

public class PointOnRectangleBorder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //        x1 < x2 и y1 < y2.

        double x1 = Double.parseDouble(sc.nextLine());
        double y1 = Double.parseDouble(sc.nextLine());
        double x2 = Double.parseDouble(sc.nextLine());
        double y2 = Double.parseDouble(sc.nextLine());
        double x = Double.parseDouble(sc.nextLine());
        double y = Double.parseDouble(sc.nextLine());

        // Border
        if (((x == x1 || x == x2) && (y >= y1 && y <= y2))
                || ((y == y1 || y == y2) && (x >= x1 && x <= x2))) {
            System.out.println("Border");
        }
        // Inside
//        else if ((x > x1 && x < x2) && (y > y1 && y < y2)) {
//            System.out.println("Inside");
//        }
        else {
            System.out.println("Inside / Outside");
        }

    }
}
