package com.dido.more;

import java.util.Scanner;

public class SquareOfStars {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int rows = Integer.parseInt(sc.nextLine());
        int cols = rows;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {

                System.out.print("* ");

            }
            System.out.println();
        }
    }
}
