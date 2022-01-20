package com.dido.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StringMatrixRotation {

    public static char[][] matrix;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String input  =sc.nextLine();

        int grads = -1;
        if (input.contains("Rotate")) {
            int startPos = input.indexOf('(')+1;
            int endPos = input.indexOf(')');

            grads = Integer.parseInt(input.substring(startPos,endPos));
        }

        input = sc.nextLine();
        List<String> textList = new LinkedList<>();
        int maxLen = 0;
        while(!input.equals("END")) {

            textList.add(input);

            if (maxLen < input.length()) {
                maxLen = input.length();
            }
            input = sc.nextLine();
        }

        matrix = new char[textList.size()][];
        int countRows = 0;
        for (String text : textList) {
            if (text.length() < maxLen) {
                int lenDif = maxLen - text.length();

                for (int i = 0; i < lenDif; i++) {
                    text += " ";
                }

            }

            matrix[countRows] = text.toCharArray();
            countRows++;
        }

        if (grads % 360 == 0) {
            printMatrix();
        }
        else if (grads % 270 == 0) {
            int rounds = grads / 270;
            for (int i = 0; i < rounds; i++) {
                rotateMatrixBy270();
            }

            printMatrix();
        } else if (grads % 180 == 0) {
            int rounds = grads / 180;
            for (int i = 0; i < rounds; i++) {
                rotateMatrixBy180();
            }

            printMatrix();

        } else if (grads % 90 == 0) {
            int rounds = grads / 90;
            for (int i = 0; i < rounds; i++) {
                rotateMatrixBy90();
            }

            printMatrix();
        }


    }

    public static void rotateMatrixBy270() {

        char[][] rotatedMatrix = new char[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                rotatedMatrix[j][i] = matrix[i][matrix[i].length - 1 - j];
            }
        }

//        printMatrix(rotatedMatrix);

        matrix = rotatedMatrix;

    }

    public static void rotateMatrixBy180() {

        char[][] rotatedMatrix = new char[matrix.length][matrix[0].length];

        for (int i = matrix.length - 1; i >=0; i--) {

            for (int j = matrix[i].length-1; j >=0; j--) {

                rotatedMatrix[matrix.length - 1 - i][matrix[i].length - 1 - j] = matrix[i][j];
            }
        }

        matrix = rotatedMatrix;

    }

    public static void rotateMatrixBy90() {
        char[][] rotatedMatrix = new char[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                rotatedMatrix[i][j] = matrix[matrix.length-1-j][i];
            }
        }

//        for (int i = 0; i < rotatedMatrix.length; i++) {
//
//            for (int j = 0; j < rotatedMatrix[i].length; j++) {
//
//                System.out.printf("%c ",rotatedMatrix[i][j]);
//            }
//
//            System.out.println();
//        }

        matrix = rotatedMatrix;
    }

    public static void printMatrix() {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%c",matrix[i][j]);
            }

            System.out.println();
        }
    }
}
