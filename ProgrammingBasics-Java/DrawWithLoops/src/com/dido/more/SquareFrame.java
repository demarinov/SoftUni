package com.dido.more;

import java.util.Scanner;

public class SquareFrame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {

            if (i == 1 || i == n) {
                System.out.print("+ ");
            } else {
                System.out.print("| ");
            }

            for (int j = 1; j <= n-2; j++) {

                System.out.print("- ");
            }

            if (i == n || i == 1) {
                System.out.print("+ ");
            } else {
                System.out.print("| ");
            }

            System.out.println();
        }
    }
}
