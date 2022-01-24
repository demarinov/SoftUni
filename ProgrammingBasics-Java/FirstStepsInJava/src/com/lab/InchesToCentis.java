package com.lab;

import java.util.Scanner;

public class InchesToCentis {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double i = Double.parseDouble(scan.nextLine());

        double inches = 2.54d * i;
        System.out.println(inches);
    }
}
