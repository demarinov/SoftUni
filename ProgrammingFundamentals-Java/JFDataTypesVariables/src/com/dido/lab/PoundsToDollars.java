package com.dido.lab;

import java.util.Scanner;

public class PoundsToDollars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //1 British Pound = 1.31 Dollars

        double bp = Double.parseDouble(sc.nextLine());
        double d = bp * 1.31;
        System.out.printf("%.3f",d);
    }
}
