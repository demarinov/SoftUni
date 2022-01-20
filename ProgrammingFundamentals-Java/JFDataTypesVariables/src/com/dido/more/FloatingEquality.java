package com.dido.more;

import java.util.Scanner;

public class FloatingEquality {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double eps = 0.000001d;
        double a = Double.parseDouble(sc.nextLine());
        double b = Double.parseDouble(sc.nextLine());

        double diff = Math.abs(a - b);
        boolean numsEqual = false;

        if (diff < eps) {
            numsEqual = true;
        }

        System.out.println(numsEqual ? "True" : "False");
    }
}
