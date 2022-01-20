package com.dido.lab;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MathPower {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double n = Double.parseDouble(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        System.out.println(new DecimalFormat("0.####").format(power(n, p)));
    }

    public static double power(double n, int p) {

        double result = 1.0d;
        for (int i = 1; i <= p; i++) {
            result *= n;
        }

        return result;
    }
}
