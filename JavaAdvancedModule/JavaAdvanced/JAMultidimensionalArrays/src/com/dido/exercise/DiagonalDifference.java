package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .limit(n)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }
        
        // find primary diagonal sum
        // find secondary diagonal sum
        int sumSecondary = 0;
        int sumPrimary = 0;
        for (int i = 0; i < n; i++) {
            
            sumPrimary += matrix[i][i];
            sumSecondary += matrix[n-1-i][i];
        }
        
        int diagonalDifference = Math.abs(sumPrimary - sumSecondary);

        System.out.println(diagonalDifference);

    }
}
