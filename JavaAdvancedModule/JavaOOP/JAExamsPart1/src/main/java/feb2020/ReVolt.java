package feb2020;

import java.util.Arrays;
import java.util.Scanner;

public class ReVolt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int commandNumber = Integer.parseInt(sc.nextLine());

        int[] playerPositionMatrix = {-1,-1};
        int[] finishPositionMatrix = {-1,-1};
        boolean playerPosFound = false;
        boolean finishPosFound = false;

        String[][] playerMatrix = new String[n][];
        for (int i = 0; i < n; i++) {
            playerMatrix[i] = Arrays.stream(sc.nextLine().split("")).toArray(String[]::new);

            if (!playerPosFound) {

                checkPlayerPosition(playerPositionMatrix, playerMatrix[i]);
                if (playerPositionMatrix[1] >= 0) {
                    playerPosFound = true;
                    playerPositionMatrix[0] = i;
                }
            }

            if (!finishPosFound) {
                checkFinishPosition(finishPositionMatrix, playerMatrix[i]);

                if (finishPositionMatrix[1] >= 0) {
                    finishPosFound = true;
                    finishPositionMatrix[0] = i;
                }
            }
        }

//        for (int i = 0; i < playerMatrix.length; i++) {
//            Arrays.stream(playerMatrix[i]).forEach(System.out::println);
//            System.out.println();
//        }

        boolean playerWon = false;
        // moves - down, right,up, left
        for (int i = 0; i < commandNumber; i++) {
            String command = sc.nextLine();

            move(playerMatrix,playerPositionMatrix,command);

            if (finishPositionMatrix[0] == playerPositionMatrix[0]
            && finishPositionMatrix[1] == playerPositionMatrix[1]) {
                playerWon = true;
                break;
            }
        }

        if (!playerWon) {
            System.out.println("Player lost!");
        } else {
            System.out.println("Player won!");
        }

        for (int i = 0; i < playerMatrix.length; i++) {

            Arrays.stream(playerMatrix[i]).forEach(s -> System.out.printf("%s",s));
            System.out.println();
        }
    }

    private static void move(String[][] playerMatrix, int[] playerPositionMatrix, String command) {

        int playerPositionRow = playerPositionMatrix[0];
        int playerPositionColumn = playerPositionMatrix[1];
        switch(command.toLowerCase()) {

            case "down":
                playerPositionRow++;
                if (outOfPositionMatrixCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow = 0;
                }

                if (playerOnBonusCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow++;

                    if (playerPositionRow >= playerMatrix.length) {
                        playerPositionRow = 0;
                    }
                }

                if (playerOnTrapCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow--;
                    if (playerPositionRow < 0) {
                        playerPositionRow = playerMatrix.length - 1;
                    }
                }
                break;
            case "up":
                playerPositionRow--;
                if (outOfPositionMatrixCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow = playerMatrix.length-1;
                }

                if (playerOnBonusCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow--;

                    if (playerPositionRow < 0) {
                        playerPositionRow = playerMatrix.length-1;
                    }

                }

                if (playerOnTrapCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionRow++;
                    if (playerPositionRow >= playerMatrix.length) {
                        playerPositionRow = 0;
                    }

                }

                break;
            case "right":
                playerPositionColumn++;

                if (outOfPositionMatrixCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn = 0;
                }

                if (playerOnBonusCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn++;

                    if (playerPositionColumn >= playerMatrix.length) {
                        playerPositionColumn = 0;
                    }
                }

                if (playerOnTrapCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn--;
                    if (playerPositionColumn < 0) {
                        playerPositionColumn = playerMatrix.length - 1;
                    }
                }

                break;
            case "left":
                playerPositionColumn--;

                if (outOfPositionMatrixCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn = playerMatrix.length-1;
                }

                if (playerOnBonusCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn--;
                    if (playerPositionColumn < 0) {
                        playerPositionColumn = playerMatrix.length-1;
                    }

                }

                if (playerOnTrapCheck(playerMatrix, playerPositionRow, playerPositionColumn)) {
                    playerPositionColumn++;
                    if (playerPositionColumn >= playerMatrix.length) {
                        playerPositionColumn = 0;
                    }

                }
                break;
        }

        playerMatrix[playerPositionMatrix[0]][playerPositionMatrix[1]] = "-";

        playerPositionMatrix[0] = playerPositionRow;
        playerPositionMatrix[1] = playerPositionColumn;

        playerMatrix[playerPositionMatrix[0]][playerPositionMatrix[1]] = "f";
    }

    private static boolean playerOnTrapCheck(String[][] playerMatrix, int playerPositionRow,
                                              int playerPositionColumn) {

        // bonus check
        if (playerMatrix[playerPositionRow][playerPositionColumn].equals("T")) {
            return true;
        }

        return false;
    }

    private static boolean playerOnBonusCheck(String[][] playerMatrix, int playerPositionRow,
                                                    int playerPositionColumn) {

        // bonus check
        if (playerMatrix[playerPositionRow][playerPositionColumn].equals("B")) {
            return true;
        }

        return false;
    }

    private static boolean outOfPositionMatrixCheck(String[][] playerMatrix, int playerPositionRow,
                                            int playerPositionColumn) {

        if (playerMatrix.length <= playerPositionRow || playerPositionRow < 0
        || playerMatrix[0].length <= playerPositionColumn || playerPositionColumn < 0) {
            return true;
        }

        return false;
    }

    private static void checkFinishPosition(int[] finishPositionMatrix, String[] playerMatrixRow) {

        for (int i = 0; i < playerMatrixRow.length; i++) {
            String item = playerMatrixRow[i];

            if (item.equals("F")) {
                finishPositionMatrix[1] = i;
                return;
            }
        }
    }

    private static void checkPlayerPosition(int[] playerPositionMatrix, String[] playerMatrixRow) {

        for (int i = 0; i < playerMatrixRow.length; i++) {
            String item = playerMatrixRow[i];

            if (item.equals("f")) {
                playerPositionMatrix[1] = i;
                return;
            }
        }
    }
}
