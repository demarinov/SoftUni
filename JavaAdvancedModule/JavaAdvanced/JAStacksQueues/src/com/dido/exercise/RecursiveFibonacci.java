package com.dido.exercise;

import java.util.Scanner;

public class RecursiveFibonacci {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        long[] state = new long[n * n];
        long result = fibonacci(n, state);
        System.out.println(result);
    }

    public static long fibonacci(int n, long[] state) {

        if (n <= 1) {
            return 1;
        } else if (state[n-1] != 0) {
            return state[n-1];
        }

        long result = fibonacci(n-1, state) + fibonacci(n-2, state);
        state[n-1] = result;
        return result;

    }
}
