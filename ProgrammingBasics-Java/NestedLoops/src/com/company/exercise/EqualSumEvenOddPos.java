package com.company.exercise;

import java.util.Scanner;

public class EqualSumEvenOddPos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());


        // m > n

        for (int i = n; i <= m; i++) {
            int rem = i % 10;
            int div = i;
            int pos = 0;
            int sumEven = 0;
            int sumOdd = 0;

            while(div >= 1) {

                rem = div % 10;
                div = div / 10;

                if (pos % 2 == 0) {
                    sumEven += rem;
                } else {
                    sumOdd += rem;
                }


                pos++;

            }


            if (sumEven == sumOdd) {
                System.out.print(i+" ");
            }

        }
    }
}
