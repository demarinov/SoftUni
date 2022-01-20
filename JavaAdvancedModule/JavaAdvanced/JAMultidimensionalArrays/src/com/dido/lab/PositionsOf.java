package com.dido.lab;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class PositionsOf {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] rowsCols = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int[][] matrix = new int[rowsCols[0]][];
        for (int i = 0; i < rowsCols[0]; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s+"))
                    .limit(rowsCols[1])
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        int num = Integer.parseInt(sc.nextLine());

        boolean isFound = false;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                if (matrix[i][j] == num) {
                    System.out.printf("%d %d%n",i,j);
                    isFound = true;
                }
            }
        }

        if (!isFound) {
            System.out.println("not found");
        }
    }
}
