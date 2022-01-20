package com.dido.exercise;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// needs quite much improvement ...
public class RadioactiveBunnies {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] size = sc.nextLine().split("\\s");

        int n = Integer.parseInt(size[0]);
        int r = Integer.parseInt(size[1]);

        String[][] bunnyLair = new String[n][r];
        for (int i = 0; i < n; i++) {

            String element = sc.nextLine();


            for (int j = 0; j < element.length(); j++) {

                bunnyLair[i][j] = String.format("%c",element.charAt(j));
            }
//            bunnyLair[i] = arrList.toArray(new String[0]);

        }

        int[] playerCoordinates = findPlayerCoordinates(bunnyLair);

        if (playerCoordinates != null) {
            System.out.println();
            Arrays.stream(playerCoordinates)
                    .mapToObj(d -> String.format("%d ", d))
                    .forEach(System.out::print);
        }



        String command = sc.nextLine();

        for (int i = 0; i < command.length(); i++) {

            char ch = command.charAt(i);

            movePlayer(bunnyLair, ch, playerCoordinates);

        }
    }

    public static int[] findPlayerCoordinates(String[][] bunnyInfo) {

        int[] playerCoordinates = new int[2];

        for (int i = 0; i < bunnyInfo.length; i++) {

            for (int j = 0; j < bunnyInfo[i].length; j++) {

                if (bunnyInfo[i][j].equals("P")) {

                    playerCoordinates[0] = i;
                    playerCoordinates[1] = j;
                    return playerCoordinates;
                }
            }
        }

        return null;
    }

    public static void movePlayer(String[][] bunnyInfo, char c, int[] playerCoordinates) {

        int rowPosition = -1;
        int colPosition = -1;
        switch(c) {

            case 'U':

                rowPosition = --playerCoordinates[0];
                colPosition = playerCoordinates[1];
                if (rowPosition < 0) {
                    // out of bounds and won
                    return;
                }


                break;
            case 'D':
                // dsfsf
                rowPosition = ++playerCoordinates[0];
                colPosition = playerCoordinates[1];
                if (rowPosition >= bunnyInfo.length) {
                    // out of bounds and won
                    return;
                }

                break;
            case 'L':
                // fdafa
                rowPosition = playerCoordinates[0];
                colPosition = --playerCoordinates[1];
                if (colPosition < 0) {
                    // out of bounds and won
                    return;
                }

                break;
            case 'R':
                // aswqqe
                rowPosition = playerCoordinates[0];
                colPosition = ++playerCoordinates[1];
                if (colPosition >= bunnyInfo.length) {
                    // out of bounds won
                    return;
                }

                break;
            default:
                // sdfdsa
                break;
        }

        if (bunnyInfo[rowPosition][colPosition].equals("B")) {

            // player killed by bunny
            return;
        }

        // player reaches out of lair and lives...
        System.out.println(playerCoordinates[0]);

        spreadBunnies(bunnyInfo, playerCoordinates);
    }

    public static void spreadBunnies(String[][] bunnyInfo, int[] playerCoordinates) {

//        bunnies spread to the up, down, left and right

        // foreach bunny go up, down, left and right

        List<String> bunnyCoordinates = new LinkedList<>();
        boolean playerKilled = false;
        boolean playerWon = false;
        // find bunny coordinates
        for (int i = 0; i < bunnyInfo.length; i++) {

            for (int j = 0; j < bunnyInfo[i].length; j++) {

                if (bunnyInfo[i][j].equals("B")) {
                    bunnyCoordinates.add(String.format("%d,%d",i,j));
                }
            }
        }


        for (int i = 0; i < bunnyCoordinates.size(); i++) {

            String[] rowsCols = bunnyCoordinates.get(i).split(",");
            int rowPos = Integer.parseInt(rowsCols[0]);
            int colPos = Integer.parseInt(rowsCols[1]);

            // spread bunny up
            int nextRowPos = rowPos - 1;

            if (nextRowPos == playerCoordinates[0] && colPos == playerCoordinates[1]) {
                // player is killed
                playerKilled = true;
                break;
            }
            if (nextRowPos >= 0) {

                if (bunnyInfo[nextRowPos][colPos].equals(".")) {
                    // spread
                    bunnyInfo[nextRowPos][colPos] = "B";
                }
            } else {
                playerWon  = true;
            }

            // spread bunny down
            nextRowPos = rowPos + 1;
            if (nextRowPos == playerCoordinates[0] && colPos == playerCoordinates[1]) {
                // player is killed
                playerKilled = true;
                break;
            }

            if (nextRowPos < bunnyInfo.length) {

                if (bunnyInfo[nextRowPos][colPos].equals(".")) {
                    // spread
                    bunnyInfo[nextRowPos][colPos] = "B";
                }
            } else {
                // player escapes the layer and won
                playerWon = true;
            }

            // spread bunny left
            int nextColPos = colPos -1;
            if (rowPos == playerCoordinates[0] && nextColPos == playerCoordinates[1]) {
                // player is killed
                playerKilled = true;
                break;
            }

            if (nextColPos >= 0) {
                if (bunnyInfo[rowPos][nextColPos].equals(".")) {
                    // spread
                    bunnyInfo[rowPos][nextColPos] = "B";
                }
            } else {
                playerWon = true;
            }

            // spread bunny right
            nextColPos = colPos + 1;
            if (rowPos == playerCoordinates[0] && nextColPos == playerCoordinates[1]) {
                // player is killed
                playerKilled = true;
                break;
            }

            if (nextColPos >= 0) {
                if (bunnyInfo[rowPos][nextColPos].equals(".")) {
                    // spread
                    bunnyInfo[rowPos][nextColPos] = "B";
                }
            } else {
                playerWon = true;
            }


        }

        if (playerKilled) {

        }

        if (playerWon) {

        }
    }

}
