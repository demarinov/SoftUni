package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class MaxSumOf2x2Matrix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] sizes = sc.nextLine().split(",\\s*");
        int r = Integer.parseInt(sizes[0]);
        int c = Integer.parseInt(sizes[1]);

        int[][] matrix = new int[r][];
        for (int i = 0; i < r; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split(",\\s*"))
                    .limit(c)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        int sum = 0;
        int rightIdx = -1;
        int leftIdx = -1;

        for (int i = 0; i < matrix.length-1; i++) {

            for (int j = 0; j < matrix[i].length-1; j++) {

                int sumCalculation = matrix[i][j] + matrix[i+1][j] + matrix[i][j+1] + matrix[i+1][j+1];
                if (sum < sumCalculation) {
                    sum = sumCalculation;
                    rightIdx = j;
                    leftIdx = i;
                }
            }
        }

        System.out.println(matrix[leftIdx][rightIdx] + " "+matrix[leftIdx][rightIdx+1]);
        System.out.println(matrix[leftIdx+1][rightIdx] + " "+matrix[leftIdx+1][rightIdx+1]);
        System.out.println(sum);

    }
}
