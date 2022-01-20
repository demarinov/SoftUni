package Oct2019;

import java.util.Arrays;
import java.util.Scanner;

public class BookWorm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder text = new StringBuilder();
               text.append(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());

        String[][] field = new String[n][];

        int[] playerPosition = {-1,-1};

        for (int i = 0; i < n; i++) {

            String[] arr = Arrays.stream(sc.nextLine().split(""))
                    .toArray(String[]::new);

            boolean playerFound = findPlayer(arr, playerPosition);

            if (playerFound) {
                // set row
                playerPosition[0] = i;
            }

            field[i] = arr;
        }

//        printField(field);

        String command = sc.nextLine();

        while(!"end".equals(command)) {

            moveAndConsume(field, playerPosition, command, text);

            command = sc.nextLine();

        }

        System.out.println(text);
        printField(field);
    }

    private static boolean checkLetter(char c) {
        return Character.isLetter(c);

    }

    private static boolean outOfRange(int pos, int min, int max) {

        if (pos < min || pos >= max) {
            return true;
        }

        return false;
    }

    public static void moveAndConsume(String[][] field, int[] playerPosition, String command, StringBuilder text) {

        int moveRow = playerPosition[0];
        int moveColumn = playerPosition[1];
        switch(command) {

            case "up":
                moveRow--;

                if (outOfRange(moveRow, 0, field.length)) {
                    if (text.length() > 0) {
                        text = text.deleteCharAt(text.length()-1);
                    }

                    moveRow++;
                } else {

                    if (checkLetter(field[moveRow][moveColumn].charAt(0))) {
                        // consume it
                        text.append(field[moveRow][moveColumn]);
                    }
                }

                break;
            case "down":
                moveRow++;


                if (outOfRange(moveRow, 0, field.length)) {
                    if (text.length() > 0) {
                        text = text.deleteCharAt(text.length()-1);
                    }

                    moveRow--;
                } else {
                    if (checkLetter(field[moveRow][moveColumn].charAt(0))) {
                        // consume it
                        text.append(field[moveRow][moveColumn]);
                    }
                }

                break;
            case "left":
                moveColumn--;

                if (outOfRange(moveColumn, 0, field.length)) {
                    if (text.length() > 0) {
                        text = text.deleteCharAt(text.length()-1);
                    }

                    moveColumn++;
                } else {

                    if (checkLetter(field[moveRow][moveColumn].charAt(0))) {
                        // consume it
                        text.append(field[moveRow][moveColumn]);
                    }
                }

                break;
            case "right":
                moveColumn++;

                if (outOfRange(moveColumn, 0, field.length)) {
                    if (text.length() > 0) {
                        text = text.deleteCharAt(text.length()-1);
                    }

                    moveColumn--;
                } else {

                    if (checkLetter(field[moveRow][moveColumn].charAt(0))) {
                        // consume it
                        text.append(field[moveRow][moveColumn]);
                    }
                }
                break;
        }

        field[playerPosition[0]][playerPosition[1]] = "-";

        playerPosition[0] = moveRow;
        playerPosition[1] = moveColumn;

        field[playerPosition[0]][playerPosition[1]] = "P";
    }

    public static void printField(String[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.printf("%s",field[i][j]);
            }
            System.out.println();
        }
    }

    public static boolean findPlayer(String[] arr, int[] playerPosition) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals("P")) {
                // set column
                playerPosition[1] = i;
                return true;
            }
        }

        return false;
    }
}
