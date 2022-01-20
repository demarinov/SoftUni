package com.dido.more;

import java.util.Scanner;

public class CircleAreaPer {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double r = Double.parseDouble(sc.nextLine());

        System.out.printf("%.2f%n",(Math.PI * r * r));
        System.out.printf("%.2f",(2.0 * Math.PI * r));

    }
}
