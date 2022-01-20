package com.dido.lab;

import java.util.Scanner;

public class RectangleArea {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double width = Double.parseDouble(sc.nextLine());
        double length = Double.parseDouble(sc.nextLine());

        double area = getRectangleArea(width,length);
        System.out.printf("%.0f",area);
    }

    public static  double getRectangleArea(double w, double l) {

        return w * l;
    }
}
