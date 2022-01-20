package com.dido.more;

import java.util.Scanner;

public class Tribonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        long[] state = new long[n];

        for (int i = 1; i <= n; i++) {
            System.out.printf("%d ",tribonacci(i, state));
        }
    }

    public static long tribonacci(int n, long[] state) {

        if (n < 1) {
            return 0;
        } else if (n == 2 || n == 1) {
            return 1;
        } else if (state[n-1] != 0) {
            return state[n-1];
        }

        long result = tribonacci(n-1, state) + tribonacci(n-2, state) + tribonacci(n-3, state);
        state[n-1] = result;
        return result;

    }
}
