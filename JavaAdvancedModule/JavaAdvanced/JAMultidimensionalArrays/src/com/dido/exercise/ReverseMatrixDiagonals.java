package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseMatrixDiagonals {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String[] size = sc.nextLine().split("\\s");

        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);

        int[][] matrix = new int[r][];

        for (int i = 0; i < r; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .limit(c).mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        // foreach element starting from last on last row
        // check up and forward and then down and backward
        // until limit is reached - row or column

        StringBuilder outputBuilder = new StringBuilder();

        for (int i = matrix[r-1].length-1; i >=0 ; i--) {

            // print first
//            System.out.printf("%d ",matrix[r-1][i]);
            outputBuilder.append(matrix[r-1][i]);
            outputBuilder.append(" ");

            // look for others
            int rowUp = r-2;
            int colUp = i + 1;

            while(true) {

                if (rowUp < 0 || colUp >= matrix[rowUp].length) {
                    break;
                }

//                System.out.printf("%d ",matrix[rowUp][colUp]);
                outputBuilder.append(matrix[rowUp][colUp]);
                outputBuilder.append(" ");
                rowUp--;
                colUp++;
            }

//            System.out.println();
            outputBuilder.append("\n");


        }

        for (int i = 0; i < r-1; i++) {
            // move up on last bit
            int rowUp = r-2-i;
            int colUp = 0;

            while(true) {
                if (rowUp < 0 || colUp >= matrix[rowUp].length) {
                    break;
                }

//                System.out.printf("%d ",matrix[rowUp][colUp]);
                outputBuilder.append(matrix[rowUp][colUp]);
                outputBuilder.append(" ");
                rowUp--;
                colUp++;
            }

//            System.out.println();
            outputBuilder.append("\n");
        }

        System.out.print(outputBuilder);
    }
}
