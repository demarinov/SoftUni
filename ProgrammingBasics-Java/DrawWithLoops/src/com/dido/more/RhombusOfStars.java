package com.dido.more;

import java.util.Scanner;

public class RhombusOfStars {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {

            // first part spaces
            for (int j = 1; j <=n-i; j++) {
                System.out.print(" ");
            }

            // first part stars
            for (int j = 1; j <=i; j++) {
                System.out.print("* ");
            }
            System.out.println();

        }

        for (int i = 1; i <= n-1; i++) {

            // second part spaces
            for (int j = 1; j <= i; j++) {
                System.out.print(" ");
            }

            // second part stars
            for (int j = n-1; j >=i; j--) {

                System.out.print("* ");
            }

            System.out.println();
        }
    }
}
