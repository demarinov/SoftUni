package com.dido.more;

import java.util.Scanner;

public class Diamond {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        // first two cases taken care of
        if (n == 1 || n == 2) {
            for (int i = 1; i <=n; i++) {
                System.out.print("*");
            }
        } else {
            // n>2

            // top part
            int rows = (n-1)/2;
            int countRows = 0;

            for (int i = 1; i <=rows; i++) {

                // left part
                for (int j = i; j <= rows; j++) {
                    System.out.print("-");
                }

                // middle part
                int rowsMiddle = n - (2 * rows) + countRows - 2;
                if (i == 1 && n % 2 == 0 || (i != 1)) {
                    System.out.print("*");
                }
                for (int j = 1; j <= rowsMiddle; j++) {
                    System.out.print("-");
                }
                System.out.print("*");

                // right part
                for (int j = i; j <= rows; j++) {
                    System.out.print("-");
                }
                System.out.println();
                countRows = countRows + 2;
            }


            // middle part
            System.out.print("*");
            for (int i = 1; i <=n-2; i++) {
                System.out.print("-");
            }
            System.out.print("*");
            System.out.println();

            // bottom part
            for (int i = 1; i <=rows; i++) {

                // left part
                for (int j = 1; j <=i; j++) {
                    System.out.print("-");
                }

                // middle part
                int bottomDashes = n - (2 * i) - 2;
                System.out.print("*");
                for (int j = 1; j <=bottomDashes; j++) {
                    System.out.print("-");
                }

                if (i == rows && n % 2 == 0 || (i != rows)) {
                    System.out.print("*");
                }

                // right part

                for (int j = 1; j <=i; j++) {
                    System.out.print("-");
                }



                System.out.println();

            }



        }
    }
}
