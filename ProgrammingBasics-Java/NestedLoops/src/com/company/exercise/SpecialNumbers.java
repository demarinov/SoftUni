package com.company.exercise;

import java.util.Scanner;

public class SpecialNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String spNumStr = "";

        // sn 1111 - 9999
        // n divided by each of 4 digits to be special

        // Ex. n=16 -> 2418, n=11 -> 1111

        for (int i = 1; i <= 9; i++) {

            // get the special number
            if (n % i == 0) {
                // store digit
                spNumStr = String.format("%s%d", spNumStr, i);
            }
        }

        if (spNumStr.length() >= 1) {
            char c1,c2,c3,c4;

            for (int j = 0; j < spNumStr.length(); j++) {
                c1 = spNumStr.charAt(j);
                for (int k = 0; k < spNumStr.length(); k++) {
                    c2 = spNumStr.charAt(k);
                    for (int l = 0; l < spNumStr.length(); l++) {
                        c3 = spNumStr.charAt(l);
                        for (int m = 0; m < spNumStr.length(); m++) {
                            c4 = spNumStr.charAt(m);
                            System.out.printf("%c%c%c%c ",c1,c2,c3,c4);
                        }
                        //System.out.print(" ");
                    }
                }
               // System.out.println();
            }

           // System.out.println(spNumStr);
        }


    }
}
