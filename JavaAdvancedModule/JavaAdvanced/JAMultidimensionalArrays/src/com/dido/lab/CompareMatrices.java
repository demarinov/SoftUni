package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] rowCols = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int[][] matrixOne = new int[rowCols[0]][rowCols[1]];
        for (int i = 0; i < rowCols[0]; i++) {

            String[] columns = sc.nextLine().split("\\s+");

            for (int j = 0; j < rowCols[1]; j++) {
                matrixOne[i][j] = Integer.parseInt(columns[j]);
            }
        }

        rowCols = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int[][] matrixTwo = new int[rowCols[0]][rowCols[1]];
        for (int i = 0; i < rowCols[0]; i++) {

            String[] columns = sc.nextLine().split("\\s+");

            for (int j = 0; j < rowCols[1]; j++) {
                matrixTwo[i][j] = Integer.parseInt(columns[j]);
            }
        }

        boolean result = compareMatrices(matrixOne, matrixTwo);

        if (result) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }

    }

    public static boolean compareMatrices(int[][] matrixOne, int[][] matrixTwo) {

        if (matrixTwo.length != matrixOne.length) {
            return false;
        }

        for (int i = 0; i < matrixOne.length; i++) {

            if (matrixOne[i].length != matrixTwo[i].length) {
                return false;
            }
            for (int j = 0; j < matrixOne[i].length; j++) {

                if (matrixOne[i][j] != matrixTwo[i][j]) {
                    return false;
                }
            }
        }


        return true;
    }
}
