package com.dido.lab;

import java.util.Scanner;

public class RecursiveFactorial {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n  = Integer.parseInt(sc.nextLine());

        System.out.println(factorial(n));
    }

    public static int factorial(int n) {

        if (n == 1) {
            return 1;
        }

        return n * factorial(n-1);
    }
}
