package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class FindRealQueen {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] chessMatrix = new char[8][];
        for (int i = 0; i < 8; i++) {

            String[] strArray = sc.nextLine().split("\\s");

            char[] arr = new char[8];
            for (int j = 0; j < 8; j++) {

                arr[j] = strArray[j].charAt(0);
            }

            chessMatrix[i] = arr;
        }

        for (int i = 0; i < chessMatrix.length; i++) {

            for (int j = 0; j < chessMatrix[i].length; j++) {

                char element = chessMatrix[i][j];


                if (element == 'q') {
                    boolean foundBlockingQueen = false;

                    // move up
                    foundBlockingQueen = searchUp(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move down
                    foundBlockingQueen = searchDown(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move left
                    foundBlockingQueen = searchLeft(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move right
                    foundBlockingQueen = searchRight(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move right up diag
                    foundBlockingQueen = searchRightUp(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move right down diag
                    foundBlockingQueen = searchRightDown(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move left up diag
                    foundBlockingQueen = searchLeftUp(chessMatrix, i, j);
                    if (foundBlockingQueen) {
                        continue;
                    }
                    // move left down diag
                    foundBlockingQueen = searchLeftDown(chessMatrix, i, j);

                    if (foundBlockingQueen) {
                        continue;
                    } else {

                        System.out.printf("%d %d",i,j);
                    }
                }

            }
        }
    }

    public static boolean searchLeftDown(char[][] matrix, int row, int col) {

        int nextCol = col-1;
        for (int i = row + 1; i < matrix.length; i++) {

            if (nextCol < 0) {
                return false;
            }
            if (matrix[i][nextCol] == matrix[row][col]) {
                return true;
            }

            nextCol--;
        }

        return false;
    }

    public static boolean searchLeftUp(char[][] matrix, int row, int col) {

        int nextCol = col-1;
        for (int i = row - 1; i >= 0; i--) {

            if (nextCol < 0) {
                return false;
            }

            if (matrix[i][nextCol] == matrix[row][col]) {
                return true;
            }

            nextCol--;
        }


        return false;
    }

    public static boolean searchRightDown(char[][] matrix, int row, int col) {

        int nextCol = col+1;
        for (int i = row + 1; i < matrix.length; i++) {

            if (nextCol >= matrix[i].length) {
                return false;
            }
            if (matrix[i][nextCol] == matrix[row][col]) {
                return true;
            }

            nextCol++;
        }

        return false;
    }

    public static boolean searchRightUp(char[][] matrix, int row, int col) {

        int nextCol = col+1;
        for (int i = row - 1; i >= 0; i--) {

            if (nextCol >= matrix[i].length) {

                return false;
            }

            if (matrix[i][nextCol] == matrix[row][col]) {
                return true;
            }

            nextCol++;
        }

        return false;
    }

    public static boolean searchRight(char[][] matrix, int row, int col) {

        for (int i = col + 1; i < matrix[row].length; i++) {

            if (matrix[row][i] == matrix[row][col]) {
                return true;
            }
        }

        return false;
    }

    public static boolean searchLeft(char[][] matrix, int row, int col) {

        for (int i = col - 1; i >= 0; i--) {

            if (matrix[row][i] == matrix[row][col]) {
                return true;
            }
        }

        return false;
    }

    public static boolean searchDown(char[][] matrix, int row, int col) {

        for (int i = row + 1; i < matrix.length; i++) {

            if (matrix[i][col] == matrix[row][col]) {
                return true;
            }
        }

        return false;
    }

    public static boolean searchUp(char[][] matrix, int row, int col) {

        for (int i = row - 1; i >= 0; i--) {

            if (matrix[i][col] == matrix[row][col]) {
                return true;
            }
        }

        return false;
    }
}
