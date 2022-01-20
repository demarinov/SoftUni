package com.dido.more;

import java.util.Scanner;

public class TriangleArea {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a = Double.parseDouble(sc.nextLine());
        double h = Double.parseDouble(sc.nextLine());

        System.out.printf("%.2f%n",(a * h/2));
    }
}
