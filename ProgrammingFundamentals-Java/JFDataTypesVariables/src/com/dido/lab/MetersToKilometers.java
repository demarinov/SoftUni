package com.dido.lab;

import java.util.Scanner;

public class MetersToKilometers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        double km = (double)m/1000.0;

        System.out.printf("%.2f",km);
    }
}
