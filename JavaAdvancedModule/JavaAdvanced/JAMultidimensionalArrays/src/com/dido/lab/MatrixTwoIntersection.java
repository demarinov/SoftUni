package com.dido.lab;

import java.util.Scanner;

public class MatrixTwoIntersection {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        char[][] matrixOne = new char[m][];
        for (int i = 0; i < m; i++) {

            String[] strArray = sc.nextLine().split("\\s");

            char[] arr = new char[n];
            for (int j = 0; j < n; j++) {

                arr[j] = strArray[j].charAt(0);
            }


            matrixOne[i] = arr;
        }

        char[][] matrixTwo = new char[m][];
        for (int i = 0; i < m; i++) {

            String[] strArray = sc.nextLine().split("\\s");

            char[] arr = new char[n];
            for (int j = 0; j < n; j++) {

                arr[j] = strArray[j].charAt(0);
            }

            matrixTwo[i] = arr;
        }

        // find intersection
        char[][] resultMatrix = new char[m][n];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (matrixOne[i][j] == matrixTwo[i][j]) {
                    resultMatrix[i][j] = matrixOne[i][j];
                } else {
                    resultMatrix[i][j] = '*';
                }
            }
        }

        for (int i = 0; i < resultMatrix.length; i++) {

            for (int j = 0; j < resultMatrix[i].length; j++) {

                System.out.printf("%c ",resultMatrix[i][j]);
            }
            System.out.println();
        }

    }
}
