package com.company.lab;

import java.util.Scanner;

public class Areas {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String figure = sc.nextLine();
        double area =0.0d;

        if (figure.equals("square")) {

            double side = Double.parseDouble(sc.nextLine());

            area = side * side;

        } else if (figure.equals("rectangle")) {
            double side1 = Double.parseDouble(sc.nextLine());
            double side2 = Double.parseDouble(sc.nextLine());
            area = side1 * side2;

        } else if (figure.equals("circle")) {
            double radius = Double.parseDouble(sc.nextLine());
            area = radius * radius * Math.PI;

        } else if (figure.equals("triangle")) {
            double side = Double.parseDouble(sc.nextLine());
            double height = Double.parseDouble(sc.nextLine());

            area = side * height/2;
        }

        System.out.printf("%.3f",area);
    }
}
