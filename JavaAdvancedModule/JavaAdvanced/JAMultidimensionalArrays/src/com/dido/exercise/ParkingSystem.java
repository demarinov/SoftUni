package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ParkingSystem {

    public static BufferedReader reader =  new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {


        String[] size = reader.readLine().split("\\s");
        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);

        // -1 parking spot is busy, 0 spot is free
        boolean[][] matrixBusySpots = new boolean[r][c];
        StringBuilder outputBuilder = new StringBuilder();

        String[] input = reader.readLine().split("\\s");

        while(!input[0].equals("stop")) {

            int entryRowIdx = Integer.parseInt(input[0]);

            int targetRow = Integer.parseInt(input[1]);
            int targetCol = Integer.parseInt(input[2]);

            if (targetCol == 0) {

                input = reader.readLine().split("\\s");
                continue;
            }

//            boolean found;
            // free, get count of steps
            int countDistance = Math.abs(targetRow - entryRowIdx)+1 + targetCol;
            
            if (!matrixBusySpots[targetRow][targetCol]) {

                System.out.println(countDistance);
                matrixBusySpots[targetRow][targetCol] = true;
//                found = true;
            } else {

                // check left and right columns

                int steps = 0;

                while (true) {

                    steps++;
                    int moveLeft = targetCol - steps;
                    int moveRight = targetCol + steps;


                    if (moveLeft < 1 && moveRight >= matrixBusySpots[0].length) {

//                        found = false;
                        System.out.printf("Row %d full%n",targetRow);
                        break;
                    }

                    if (moveLeft >= 1 && !matrixBusySpots[targetRow][moveLeft]) {
                        countDistance -= steps;
//                        found = true;
                        System.out.println(countDistance);
                        matrixBusySpots[targetRow][moveLeft] = true;
                        break;
                    }

                    if (moveRight < matrixBusySpots[0].length && !matrixBusySpots[targetRow][moveRight]) {

                        countDistance += steps;
//                        found = true;
                        System.out.println(countDistance);
                        matrixBusySpots[targetRow][moveRight] = true;
                        break;
                    }

                }

            }

//            if (found) {
////                System.out.println(countDistance);
//                outputBuilder.append(countDistance);
//                outputBuilder.append("\n");
//            } else {
////                System.out.printf("Row %d full%n",targetRow);
//                outputBuilder.append("Row ");
//                outputBuilder.append(targetRow);
//                outputBuilder.append(" full\n");
//            }

            input = reader.readLine().split("\\s");
        }

//        System.out.printf(outputBuilder.toString());

    }
}
