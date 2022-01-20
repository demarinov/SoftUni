package com.dido.exercise;

import java.util.Scanner;

public class FactorialDivision {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Math.abs(Integer.parseInt(sc.nextLine()));
        int n2 = Math.abs(Integer.parseInt(sc.nextLine()));

        long[] state = new long[n1];
        long f1 = factorial(n1, state);
        state = new long[n2];
        long f2 = factorial(n2, state);

        double result = 1.0 * f1 / f2;

        System.out.printf("%.2f",result);
    }

    public static long factorial(int n, long[] state) {

        if (n <= 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (state[n-1] != 0) {
            return state[n-1];
        }

        long result = n * factorial(n-1, state);
        state[n-1] = result;
        return result;

    }
}
