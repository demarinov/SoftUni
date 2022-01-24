package com.exercise;

import java.util.Scanner;

public class AngularConverter {

    public static void main(String[] args) {
        // 1 rad = 180 / PI

        Scanner scan = new Scanner(System.in);

        double rads = Double.parseDouble(scan.nextLine());

        System.out.printf("%.0f",(rads * 180/Math.PI));
    }
}
