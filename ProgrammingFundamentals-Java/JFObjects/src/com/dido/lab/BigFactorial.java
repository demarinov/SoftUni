package com.dido.lab;

import java.math.BigInteger;
import java.util.Scanner;

public class BigFactorial {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        BigInteger[] state = new BigInteger[n];
        BigInteger f = factorial(n, state);
        System.out.println(f.toString());
    }

    public static BigInteger factorial(int n, BigInteger[] state) {

        if (n <= 1) {
            return BigInteger.ONE;
        } else if (n == 2) {
            return BigInteger.TWO;
        } else if (state[n-1] != null) {
            return state[n-1];
        }

        BigInteger result = factorial(n-1, state).multiply(BigInteger.valueOf(n));
        state[n-1] = result;
        return result;

    }
}
