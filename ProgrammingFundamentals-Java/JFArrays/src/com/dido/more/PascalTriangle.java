package com.dido.more;

import java.util.Scanner;

public class PascalTriangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] prevRowElems = new int[n];

        for (int i = 0; i < n; i++) {

            int[] curRowElems = new int[n];


            for (int j = 0; j <= i; j++) {

                int elem = 0;
                int elemRightPrev = 0;
                int elemLeftPrev = 0;

                if (j == 0) {
                    elemRightPrev = 1;
                } else {
                    // calc elems
                    elemRightPrev = prevRowElems[j];
                    elemLeftPrev = prevRowElems[j - 1];
                }

                elem = elemRightPrev + elemLeftPrev;

                curRowElems[j] = elem;
            }

            prevRowElems = curRowElems;

            // print the row of elems
            for (int k = 0; k < prevRowElems.length; k++) {

                if (prevRowElems[k] != 0) {
                    System.out.printf("%d ", prevRowElems[k]);
                }
            }

            System.out.println();

        }


    }
}
