package com.dido.lab;

import java.util.Scanner;

public class MUltiplyEvensByOdds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n =Integer.parseInt(sc.nextLine());

        int absNum = Math.abs(n);

        System.out.println(sumEven(absNum) * sumOdd(absNum));
    }

    public static int sumOdd(int n) {
        int numRem = n;
        int sumOdd = 0;

        while(numRem > 0) {

            int rem = numRem % 10;
            if (rem % 2 != 0) {
                sumOdd += rem;
            }
            numRem /= 10;
        }

        return sumOdd;
    }

    public static int sumEven(int num) {

        int numRem = num;
        int sumEven =0;

        while(numRem > 0) {

            int rem = numRem % 10;
            if (rem % 2 == 0) {
                sumEven += rem;
            }
            numRem /= 10;
        }

        return sumEven;
    }
}
