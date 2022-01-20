package com.dido.more;

import java.util.Scanner;

public class House {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int topRows = 0;
        int initStep = 0;

        int upperBound = ((n-1)/2);
        int countRows = 0;
        // top part
        for (int i = 1; i <= upperBound; i++) {

            // spaces left
            for (int j = i; j <= upperBound; j++) {
                System.out.print("-");
            }

            int stars = n - (upperBound * 2) + countRows;

            for (int j = 1; j <= stars; j++) {
                System.out.print("*");
            }

            // spaces right
            for (int j = i; j <= upperBound; j++) {
                System.out.print("-");
            }

            System.out.println();
            countRows = countRows + 2;
        }

        // middle part
        for (int i = 1; i <=n; i++) {

            System.out.print("*");
        }

        System.out.println();

        // bottom part
        for (int i = 1; i <= n/2; i++) {
            System.out.print("|");
            for (int j = 1; j <=n-2; j++) {

                System.out.print("*");
            }
            System.out.print("|");
            System.out.println();
        }
    }
}
