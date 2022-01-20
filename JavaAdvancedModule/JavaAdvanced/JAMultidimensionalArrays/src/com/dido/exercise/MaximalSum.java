package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MaximalSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] size = sc.nextLine().split("\\s+");

        int n = Integer.parseInt(size[0]);
        int m = Integer.parseInt(size[1]);

        if (n <= 2 || m <= 2) {
            return;
        }

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s+"))
                    .limit(m)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        int maxSum = 0;
        int maxRowIdx = 0;
        int maxColIdx = 0;
        for (int i = 0; i < n; i++) {

            int downBoundary = i + 2;

            if (downBoundary >= matrix.length) {
                continue;
            }

            for (int j = 0; j < m; j++) {
                int rightBoundary = j + 2;

                if (rightBoundary >= matrix[i].length) {
                    continue;
                }

                // find the 3 x 3 matrix sum
                int sum = 0;
                for (int k = j; k <= rightBoundary; k++) {

                    for (int l = i; l <= downBoundary; l++) {
                        sum += matrix[l][k];
                    }
                }

                if (maxSum < sum) {
                    maxSum = sum;
                    maxRowIdx = i;
                    maxColIdx = j;
                }

            }

        }

        System.out.println("Sum = "+maxSum);

        int rightIdx = maxColIdx + 2;
        int downIdx = maxRowIdx + 2;
        for (int i = maxRowIdx; i <= downIdx; i++) {

            for (int j = maxColIdx; j <= rightIdx; j++) {

                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }

    }

}
