package com.dido.exercise;

import java.util.Scanner;

public class MatrixOfPalindromes {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] size = sc.nextLine().split("\\s");

        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);

        printPaliMatrix(buildPaliMatrix(r, c));
    }

    public static String[][] buildPaliMatrix(int r, int c) {

        char startChar = 'a';

        String[][] matrixPali = new String[r][c];
        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {

                StringBuilder builder = new StringBuilder();
                // row forms 1st char
                String firstChar = String.format("%c",(startChar+i));
                builder.append(firstChar);

                // col + row forms middle char
                String middleChar = String.format("%c",(startChar+ i + j));
                builder.append(middleChar);

                // row forms last char
                String lastChar = String.format("%c",(startChar+i));
                builder.append(lastChar);

                matrixPali[i][j] = builder.toString();
            }

        }

        return matrixPali;
    }

    public static void printPaliMatrix(String[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%s ",matrix[i][j]);
            }

            System.out.println();
        }
    }
}
