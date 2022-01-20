package com.dido.more;

import java.util.Scanner;

public class EqualPairs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int prevPairSum = Integer.MIN_VALUE;
        int maxDiff = Integer.MIN_VALUE;
        boolean hasDiffPairs = false;

        for (int i = 1; i <= n; i++) {

            int n1 = Integer.parseInt(sc.nextLine());
            int n2 = Integer.parseInt(sc.nextLine());
            int pairSum = n1 + n2;

            if (i != 1) {

                if (pairSum != prevPairSum) {
                    hasDiffPairs = true;
                }

                int pairDiff = Math.abs(pairSum - prevPairSum);

                if (maxDiff < pairDiff) {
                    maxDiff = pairDiff;
                }
            }

            prevPairSum = pairSum;



        }

        if (!hasDiffPairs) {

            System.out.println("Yes, value="+prevPairSum);
        } else {
            System.out.println("No, maxdiff="+maxDiff);
        }
    }
}
