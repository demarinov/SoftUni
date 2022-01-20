package com.dido.exercise;

import java.util.Scanner;

public class MatrixShuffling {

    public static String[][] matrix;
    public static Scanner sc = new Scanner(System.in);;
    public static int r;
    public static int c;

    public static void main(String[] args) {

        String[] size = sc.nextLine().split("\\s");

        r = Integer.parseInt(size[0]);
        c = Integer.parseInt(size[1]);

        readMatrix();

        String input = sc.nextLine();

        while (!input.equals("END")) {


            String[] data = input.split("\\s");

            if (!data[0].contains("swap") || data.length != 5) {
                System.out.println("Invalid input!");
                input = sc.nextLine();
                continue;
            }

            int row1 = Integer.parseInt(data[1]);
            int col1 = Integer.parseInt(data[2]);
            int row2 = Integer.parseInt(data[3]);
            int col2 = Integer.parseInt(data[4]);

            if (row1 >= matrix.length || row2 >= matrix.length
                    || col1 >= matrix[0].length || col2 >= matrix[0].length
                    || row1 < 0 || row2 < 0 || col1 < 0 || col2 < 0) {
                System.out.println("Invalid input!");
                input = sc.nextLine();
                continue;
            }

            String temp = matrix[row1][col1];
            matrix[row1][col1] = matrix[row2][col2];
            matrix[row2][col2] = temp;


//            printMatrix();
            System.out.print(getMatrixAsString());

            input = sc.nextLine();
        }


    }

    // perf wise better to combine the output for long matrices ..
    private static String getMatrixAsString() {
        StringBuilder output = new StringBuilder();
        for (int row = 0; row < r; row++) {
            StringBuilder rowOutput = new StringBuilder();
            for (int col = 0; col < c; col++) {
                rowOutput.append(matrix[row][col])
                        .append(" ");
            }
            output.append(rowOutput.toString().trim())
                    .append(System.lineSeparator());
        }
        return output.toString();
    }

    public static void printMatrix() {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%s ", matrix[i][j]);
            }

            System.out.println();
        }
    }

    public static void readMatrix() {

        matrix = new String[r][c];
        for (int i = 0; i < r; i++) {

            String[] arr = sc.nextLine().split("\\s+");
            for (int j = 0; j < c; j++) {

                matrix[i][j] = arr[j];
            }

        }

    }
}
