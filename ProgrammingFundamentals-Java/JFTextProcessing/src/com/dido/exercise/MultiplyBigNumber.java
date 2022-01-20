package com.dido.exercise;

import java.math.BigInteger;
import java.util.Scanner;

public class MultiplyBigNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String numStr = sc.nextLine();
        BigInteger num = new BigInteger(numStr);

        int n = Integer.parseInt(sc.nextLine());

        System.out.println(num.multiply(new BigInteger(String.valueOf(n))));
    }
}
