package com.dido.more;

import java.util.Scanner;

public class SquareFrame2 {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        // print top
        System.out.print("+ ");
        for (int i = 1; i <= n-2; i++) {
            System.out.print("- ");
        }
        System.out.print("+ ");
        System.out.println();


        // print middle
        for (int i = 1; i <= n-2; i++) {

            System.out.print("| ");

            for (int j = 1; j <= n-2; j++) {
                System.out.print("- ");
            }
            System.out.print("| ");
            System.out.println();
        }

        // print bottom
        System.out.print("+ ");
        for (int i = 1; i <= n-2; i++) {
            System.out.print("- ");
        }
        System.out.print("+ ");
        System.out.println();

    }
}
