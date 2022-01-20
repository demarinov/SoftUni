package com.dido.lab;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WrongMeasurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int d = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[d][];
        for (int i = 0; i < d; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        int[] invalidPos = Arrays.stream(sc.nextLine().split("\\s"))
                .limit(2)
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int invalidNum = matrix[invalidPos[0]][invalidPos[1]];

        Map<String, Integer> sumIndexesMap = new LinkedHashMap<>();
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                int n = matrix[i][j];

                if (n == invalidNum) {

                    int sum = 0;
                    // should be replaced
                    // go left 1
                    int leftPos = j - 1;
                    if (leftPos >= 0) {
                        int leftNum = matrix[i][leftPos];

                        if (leftNum != invalidNum) {
                            // only valid
                            sum +=leftNum;
                        }
                    }


                    // go right 1
                    int rightPos = j + 1;

                    if (rightPos <= matrix[i].length-1) {

                        int rightNum = matrix[i][rightPos];

                        if (rightNum != invalidNum) {
                            sum += rightNum;
                        }
                    }

                    // go up 1
                    int upPos = i - 1;

                    if (upPos >= 0) {
                        int upNum = matrix[upPos][j];

                        if (upNum != invalidNum) {
                            sum += upNum;
                        }
                    }

                    // go down 1
                    int downPos = i + 1;

                    if (downPos <= matrix.length-1) {
                        int downNum = matrix[downPos][j];

                        if (downNum != invalidNum) {
                            sum += downNum;
                        }
                    }

                    // store the sum and indexes, replace later
                    sumIndexesMap.putIfAbsent(String.format("%d,%d",i,j),sum);
                }

            }

        }

        for (Map.Entry<String, Integer> sumIndexes : sumIndexesMap.entrySet()) {

            String key = sumIndexes.getKey();
            int sum = sumIndexes.getValue();

            int[] pos = Arrays.stream(key.split(","))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[pos[0]][pos[1]] = sum;
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
