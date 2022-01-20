package com.company.more;

import java.util.Scanner;

public class PrimePairs {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int firstPairStart = Integer.parseInt(sc.nextLine());
        int secondPairStart = Integer.parseInt(sc.nextLine());
        int firstPairSEDiff = Integer.parseInt(sc.nextLine());
        int secondPairSEDiff = Integer.parseInt(sc.nextLine());

        for (int i = 0; i <=9 ; i++) {
            for (int j = 0; j <=9; j++) {
                for (int k = 0; k <=9; k++) {
                    for (int l = 0; l <=9; l++) {
                        int firstPair = 10 * i + j;
                        int secondPair = 10* k + l;

                        if (firstPair < firstPairStart || firstPair > (firstPairStart + firstPairSEDiff)) {
                            continue;
                        }

                        if (secondPair < secondPairStart || secondPair > (secondPairStart + secondPairSEDiff)) {
                            continue;
                        }

                        boolean isFirstPairPrime = true;
                        // check for firstPair prime num
                        for (int m = 2; m < firstPair; m++) {

                            if (firstPair % m == 0) {
                                isFirstPairPrime = false;
                                break;
                            }
                        }

                        if (!isFirstPairPrime) {
                            continue;
                        }

                        boolean isSecondPairPrime = true;
                        // check for secondPair prime num
                        for (int m = 2; m < secondPair; m++) {

                            if (secondPair % m == 0) {
                                isSecondPairPrime = false;
                                break;
                            }
                        }

                        if (!isSecondPairPrime) {
                            continue;
                        }

                        System.out.printf("%d%d%n",firstPair,secondPair);

                    }
                }
            }
        }
    }
}
