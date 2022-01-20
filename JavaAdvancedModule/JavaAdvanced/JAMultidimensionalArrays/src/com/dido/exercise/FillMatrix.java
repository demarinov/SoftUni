package com.dido.exercise;

import java.util.Scanner;

public class FillMatrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] info = sc.nextLine().split(",\\s*");

        int n = Integer.parseInt(info[0]);
        String pattern = info[1];

        fillMatrix(n, pattern);
    }

    public static void fillMatrix(int n, String pattern) {

        int[][] matrix = new int[n][n];
        if (pattern.equals("A")) {
            // 1 4 7
            // 2 5 8
            // 3 6 9

            int count = 0;
            // cols
            for (int i = 0; i < n; i++) {

                // rows
                for (int j = 0; j < n; j++) {

                    matrix[j][i] = ++count;
                }
            }

        } else if (pattern.equals("B")) {
            // 1 6 7
            // 2 5 8
            // 3 4 9

            int count = 0;
            // cols
            for (int i = 0; i < n; i++) {

                // rows
                if ((i + 1) % 2 != 0) {
                    for (int j = 0; j < n; j++) {
                        matrix[j][i] = ++count;
                    }
                } else {
                    for (int j = n-1; j >= 0 ; j--) {
                        matrix[j][i] = ++count;
                    }
                }
            }

        }

        printMatrix(matrix);
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }
    }

}
