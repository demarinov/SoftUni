package com.dido.exercise;

import java.util.Scanner;

public class BeerKegs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double volMax = 0.0d;
        String modelMax = "";

        for (int i = 1; i <= n; i++) {

            String model = sc.nextLine();
            double radius = Double.parseDouble(sc.nextLine());
            int height = Integer.parseInt(sc.nextLine());

            double vol = Math.PI * Math.pow(radius,2) * height;

            if (volMax < vol) {
                volMax = vol;
                modelMax = model;
            }
        }

        System.out.printf("%s",modelMax);
    }
}
