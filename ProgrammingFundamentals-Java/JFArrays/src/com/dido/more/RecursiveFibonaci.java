package com.dido.more;

import java.util.Scanner;

public class RecursiveFibonaci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        long[] memo = new long[n];
        if (n >= 1 && n <= 50) {
            System.out.print(fib(n, memo));
        } else if (n == 0) {
            System.out.print(0);
        }

    }

    public static long fib(int n, long[] memo) {

        if (n <= 2) {
            return 1;
        } else if (memo[n-1] != 0) {
            return memo[n-1];
        }

        long res = fib(n-1,memo) + fib(n-2,memo);
        memo[n-1] = res;

        return res;
    }
}
