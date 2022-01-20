package Jun2021;

import java.util.Arrays;
import java.util.Scanner;

// square matrix - Python
public class Problem2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String[] commands = sc.nextLine().split(",\\s*");

        String[][] field = new String[n][];

        int[] snakePosition = {-1,-1};
        int snakeLength = 1;

        boolean snakeFound = false;
        int foodToBeEaten = 0;
        for (int i = 0; i < n; i++) {

            String[] arr = Arrays.stream(sc.nextLine().split("\\s")).
                    toArray(String[]::new);
            if (!snakeFound) {
                snakeFound = findSnakePos(arr, snakePosition, i);
            }

            foodToBeEaten += findFood(arr);

            field[i] = arr;

        }

        boolean killedByEnemy = false;
        for (int i = 0; i < commands.length; i++) {

            String command = commands[i];

            move(field,snakePosition, command);

            if (field[snakePosition[0]][snakePosition[1]].equals("e")) {
                killedByEnemy = true;
                break;
            }

            snakeLength += snakeOnFood(field, snakePosition);

            if (snakeLength-1 == foodToBeEaten) {
                foodToBeEaten = 0;
                break;
            }

        }

        if (killedByEnemy) {
            System.out.printf("You lose! Killed by an enemy!%n");
        } else if (foodToBeEaten == 0) {
            System.out.printf("You win! Final python length is %d%n",snakeLength);
        } else if (foodToBeEaten > 0) {
            System.out.printf("You lose! There is still %d food to be eaten.",
                    (foodToBeEaten - (snakeLength-1)));
        }
    }

    private static int findFood(String[] fieldRow) {

        int count = 0;
        for (int i = 0; i < fieldRow.length; i++) {

            if (fieldRow[i].equals("f")) {
                count++;
            }
        }

        return count;
    }

    private static int snakeOnFood(String[][] snakeGround, int[] snakePos) {

        if (snakeGround[snakePos[0]][snakePos[1]].equals("f")) {

            snakeGround[snakePos[0]][snakePos[1]] = "s";
            return 1;
        }

        return 0;
    }

    private static void move(String[][] snakeGround,int[] snakePos, String command) {

        int snakePositionRow = snakePos[0];
        int snakePositionColumn = snakePos[1];

        switch(command) {

            case "up":
                snakePositionRow--;

                if (outOfPositionCheck(snakeGround, snakePositionRow, snakePositionColumn)) {
                    snakePositionRow = snakeGround.length-1;
                }

                break;
            case "down":
                snakePositionRow++;

                if (outOfPositionCheck(snakeGround, snakePositionRow, snakePositionColumn)) {
                    snakePositionRow = 0;
                }

                break;
            case "left":
                snakePositionColumn--;

                if (outOfPositionCheck(snakeGround, snakePositionRow, snakePositionColumn)) {
                    snakePositionColumn = snakeGround.length-1;
                }

                break;
            case "right":
                snakePositionColumn++;

                if (outOfPositionCheck(snakeGround, snakePositionRow, snakePositionColumn)) {
                    snakePositionColumn = 0;
                }

                break;
        }

        snakePos[0] = snakePositionRow;
        snakePos[1] = snakePositionColumn;

    }

    private static boolean findSnakePos(String[] arr, int[] snakePos, int currentRow) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals("s")) {
                snakePos[0] = currentRow;
                snakePos[1] = i;
                return true;
            }
        }

        return false;
    }

    private static boolean outOfPositionCheck(String[][] snakeMatrix, int snakePositionRow,
                                                    int snakePositionColumn) {

        if (snakeMatrix.length <= snakePositionRow || snakePositionRow < 0
                || snakeMatrix[0].length <= snakePositionColumn || snakePositionColumn < 0) {
            return true;
        }

        return false;
    }

}
