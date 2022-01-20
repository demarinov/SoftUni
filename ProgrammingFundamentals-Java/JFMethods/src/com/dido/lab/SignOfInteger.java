package com.dido.lab;

import java.util.Scanner;

public class SignOfInteger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        printSignOfInteger(n);

    }

    public static void printSignOfInteger(int n) {
        if (n > 0) {
            System.out.printf("The number %d is positive.",n);
        } else if (n < 0) {
            System.out.printf("The number %d is negative.",n);
        } else {
            System.out.printf("The number %d is zero.",n);
        }
    }
}
