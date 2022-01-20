package com.dido.more;

import java.util.Scanner;

public class MultiplyBy2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double num = 0.0d;

        while (true) {

            num = Double.parseDouble(sc.nextLine());

            if (num < 0) {
                System.out.println("Negative number!");
                break;
            }

            System.out.printf("Result: %.2f%n",(num * 2.0));
        }
    }
}
