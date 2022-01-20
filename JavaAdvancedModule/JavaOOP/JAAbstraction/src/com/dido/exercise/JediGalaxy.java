package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class JediGalaxy {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int x = dimensions[0];
        int y = dimensions[1];

        int[][] matrixOfStars = new int[x][y];

        populateStarsMatrix(matrixOfStars, x, y);

        String command = scanner.nextLine();
        long sumOfStars = 0;
        while (!"Let the Force be with you".equals(command)) {
            int[] jediCoordinates = Arrays.stream(command.split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int[] evilCoordinates = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int evilXPosition = evilCoordinates[0];
            int evilYPosition = evilCoordinates[1];

            destroyStars(matrixOfStars, evilXPosition, evilYPosition);

            int jediXPosition = jediCoordinates[0];
            int jediYPosition = jediCoordinates[1];

            sumOfStars += collectStars(matrixOfStars, jediXPosition, jediYPosition);

            command = scanner.nextLine();
        }

        System.out.println(sumOfStars);


    }

    public static int collectStars(int[][] matrixOfStars, int jediXPosition, int jediYPosition) {
        int sum = 0;
        while (jediXPosition >= 0 && jediYPosition < matrixOfStars[1].length) {
            if (jediXPosition >= 0 && jediXPosition < matrixOfStars.length
                    && jediYPosition >= 0 && jediYPosition < matrixOfStars[0].length) {
                sum += matrixOfStars[jediXPosition][jediYPosition];
            }

            jediYPosition++;
            jediXPosition--;
        }

        return sum;
    }

    public static void destroyStars(int[][] matrixOfStars, int evilXPosition, int evilYPosition) {

        while (evilXPosition >= 0 && evilYPosition >= 0) {
            if (evilXPosition >= 0 && evilXPosition < matrixOfStars.length
                    && evilYPosition >= 0 && evilYPosition < matrixOfStars[0].length) {
                matrixOfStars[evilXPosition][evilYPosition] = 0;
            }
            evilXPosition--;
            evilYPosition--;
        }
    }

    public static void populateStarsMatrix(int[][] matrixOfStars, int x, int y) {
        int value = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrixOfStars[i][j] = value++;
            }
        }
    }
}
