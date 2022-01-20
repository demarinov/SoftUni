package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix {

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

        int[] downwardDiagonal = new int[n];
        for (int i = 0; i < n; i++) {

            downwardDiagonal[i] = matrix[i][i];
        }

        int[] upwardDiagonal = new int[n];
        for (int i = n-1; i >= 0; i--) {

            upwardDiagonal[n-1-i] = matrix[i][n-1-i];
        }

        Arrays.stream(downwardDiagonal)
                .mapToObj(s -> String.format("%d ",s))
                .forEach(System.out::print);

        System.out.println();

        Arrays.stream(upwardDiagonal)
                .mapToObj(s -> String.format("%d ",s))
                .forEach(System.out::print);

    }
}
