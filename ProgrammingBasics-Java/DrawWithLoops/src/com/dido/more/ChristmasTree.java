package com.dido.more;

import java.util.Scanner;

public class ChristmasTree {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        // first part upper spaces
        for (int i = 1; i <=n; i++) {
            System.out.print(" ");
        }

        // first part upper |
        System.out.print(" |");
        System.out.println();

        // second part spaces and stars
        for (int i = 1; i <=n; i++) {

            // spaces to the left
            for (int j = i; j <= n-1; j++) {
                System.out.print(" ");
            }

            // stars to the left
            for (int j = 1; j <=i; j++) {
                System.out.print("*");
            }

            // middle part
            System.out.print(" | ");

            // stars to the right
            for (int j = 1; j <=i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

    }
}
