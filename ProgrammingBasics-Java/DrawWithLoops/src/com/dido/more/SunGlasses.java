package com.dido.more;

import java.util.Scanner;

public class SunGlasses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        // top part ----------------------

        // first part 2n stars to the left
        for (int i = 1; i <= 2*n; i++) {
            System.out.print("*");
        }

        // first part n spaces middle
        for (int i = 1; i <=n; i++) {
            System.out.print(" ");
        }

        // first part 2n stars to the right
        for (int i = 1; i <= 2*n; i++) {
            System.out.print("*");
        }

        // top part end ---------------

        System.out.println();


        // middle part ---------------------

        for (int i = 1; i <= n-2; i++) {

            // stars + frontlines left
            System.out.print("*");
            // frontlines
            for (int j = 1; j <= 2*n-2; j++) {
                System.out.print("/");
            }
            System.out.print("*");

            if (i == ((n-1)/2)) {
                // middle part
                for (int j = 1; j <= n; j++) {
                    System.out.print("|");
                }
            } else {
                // middle part
                for (int j = 1; j <= n; j++) {
                    System.out.print(" ");
                }
            }

            // stars + frontlines right
            System.out.print("*");
            // frontlines
            for (int j = 1; j <= 2*n-2; j++) {
                System.out.print("/");
            }
            System.out.print("*");

            System.out.println();
        }

        // middle part end -----------------

        // bottom part ----------------------

        // first part 2n stars to the left
        for (int i = 1; i <= 2*n; i++) {
            System.out.print("*");
        }

        // first part n spaces middle
        for (int i = 1; i <=n; i++) {
            System.out.print(" ");
        }

        // first part 2n stars to the right
        for (int i = 1; i <= 2*n; i++) {
            System.out.print("*");
        }

        // bottom part end ---------------
    }
}
