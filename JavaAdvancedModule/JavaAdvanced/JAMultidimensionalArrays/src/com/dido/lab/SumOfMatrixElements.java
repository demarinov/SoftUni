package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class SumOfMatrixElements {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] sizes = sc.nextLine().split(",\\s*");
        int r = Integer.parseInt(sizes[0]);
        int c = Integer.parseInt(sizes[1]);

        int[][] matrix = matrixReader(r, c, sc);

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
        }

        System.out.println(r);
        System.out.println(c);
        System.out.println(sum);

    }

    public static int[][] matrixReader(int r, int c, Scanner sc) {


        int[][] result = new int[r][];
        for (int i = 0; i < r; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split(",\\s*"))
                    .limit(c)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            result[i] = arr;
        }

        return result;
    }
}
