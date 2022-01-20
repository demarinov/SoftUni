package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Crossfire {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] size = sc.nextLine().split("\\s+");
        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);


        int[][] matrix = populateMatrixInSeqV2(r, c);
        boolean[][] destroyedCellsMatrix = new boolean[r][c];

        String input = sc.nextLine();
        while (!input.equals("Nuke it from orbit")) {
            int[] nukeData = Arrays.stream(input.split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            nukeTheMatrix(nukeData, matrix, destroyedCellsMatrix);

            input = sc.nextLine();

        }
        printNukedMatrix(matrix, destroyedCellsMatrix);
    }

    public static boolean areCoordinatesValid(int r, int c, boolean[][] matrix) {

        if (r < 0 || r >= matrix.length
                || c < 0 || c >= matrix[0].length) {
            return false;
        }

        return true;
    }

    public static int findBestRow(int r, boolean[][] destroyedCellsMatrix) {

        if (isRowDestroyed(r, destroyedCellsMatrix)) {

            // go down
            int currentRow = r;
            while (++currentRow < destroyedCellsMatrix.length) {

                if (!isRowDestroyed(currentRow, destroyedCellsMatrix)) {

                    return currentRow;
                }
            }

            // go up
            currentRow = r;
            while (--currentRow >= 0) {

                if (!isRowDestroyed(currentRow, destroyedCellsMatrix)) {
                    return currentRow;
                }
            }
        }

        return r;
    }

    public static int findBestCol(int r, int c, boolean[][] destroyedCellsMatrix) {

        if (destroyedCellsMatrix[r][c]) {

            // go right
            int currentCol = c;
            while (++currentCol < destroyedCellsMatrix.length) {

                if (!destroyedCellsMatrix[r][currentCol]) {
                    return currentCol;
                }
            }

            // go left
            currentCol = c;
            while (--currentCol >= 0) {

                if (!destroyedCellsMatrix[r][currentCol]) {
                    return currentCol;
                }
            }
        }

        return c;
    }

    public static void nukeTheMatrix(int[] nukeData, int[][] matrix, boolean[][] destroyedCellsMatrix) {


        // based on constraints coordinates can be outside
        // but still be able to nuke the matrix on rad
        int r = nukeData[0];
        int c = nukeData[1];
        int rad = nukeData[2];

        if (isMatrixNukedAll(destroyedCellsMatrix)) {
            return;
        }

        if (areCoordinatesValid(r, c, destroyedCellsMatrix)) {
            // find best row
            r = findBestRow(r, destroyedCellsMatrix);
            // find best col
            c = findBestCol(r, c, destroyedCellsMatrix);
        }

        int endRows = r + rad;
        // nuke to the up and down radius len and nuke
        for (int i = r - rad; i <= endRows; i++) {

            if (!areCoordinatesValid(i, c, destroyedCellsMatrix)
                    || destroyedCellsMatrix[i][c]) {
                continue;
            }

            if (areCoordinatesValid(i, c, destroyedCellsMatrix) && i != r) {
                destroyedCellsMatrix[i][c] = true;
            }
        }


        int endCol = r - rad;
        // nuke left and right radius len
        for (int i = c + rad; i >= endCol; i--) {
            if (!areCoordinatesValid(r, i, destroyedCellsMatrix)) {
                continue;
            }

            if (destroyedCellsMatrix[r][i]) {
                endCol--;
                continue;
            }

            if (areCoordinatesValid(r, i, destroyedCellsMatrix)) {
                destroyedCellsMatrix[r][i] = true;
            }

        }

    }

    public static boolean isRowDestroyed(int row, boolean[][] destroyedCellsMatrix) {

        for (int i = 0; i < destroyedCellsMatrix[row].length; i++) {

            if (!destroyedCellsMatrix[row][i]) {
                return false;
            }
        }

        return true;
    }

    public static int[][] populateMatrixInSeqV2(int r, int c) {

        int[][] matrix = new int[r][c];

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {

                matrix[i][j] = i * c + j + 1;
            }
        }

        return matrix;

    }

    public static boolean isMatrixNukedAll(boolean[][] destroyedCellsMatrix) {

        int countDestroyed = 0;
        for (int i = 0; i < destroyedCellsMatrix.length; i++) {

            if (isRowDestroyed(i, destroyedCellsMatrix)) {
                countDestroyed++;
            }
        }

        if (countDestroyed == destroyedCellsMatrix.length) {
            return true;
        }

        return false;
    }

    public static void printNukedMatrix(int[][] matrix, boolean[][] destroyedCellsMatrix) {

        if (isMatrixNukedAll(destroyedCellsMatrix)) {
            return;
        }

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                // print only not nuked elements
                if (!destroyedCellsMatrix[i][j]) {
                    System.out.printf("%d ", matrix[i][j]);
                }
            }

            System.out.println();
        }
    }
}
