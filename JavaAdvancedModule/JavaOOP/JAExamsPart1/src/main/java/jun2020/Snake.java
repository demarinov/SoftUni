package jun2020;

import java.util.Arrays;
import java.util.Scanner;

public class Snake {


    private static String snakeTrail = ".";
    private static String snakeFood = "*";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);



        int n = Integer.parseInt(sc.nextLine());
        String[][] snakeGround = new String[n][];
        int[] snakePos = {-1,-1};
        int[] lairPos = {-1,-1,-1,-1};


        boolean snakeFound = false;
        int lairCount = 0;

        for (int i = 0; i < snakeGround.length; i++) {

            String[] arr = Arrays.stream(sc.nextLine().split(""))
                    .toArray(String[]::new);
            // find snake pos
            if (!snakeFound) {
                snakeFound = findSnakePos(arr, snakePos, i);
            }

            if (lairCount != 2) {
                lairCount += findLair(arr, lairPos, i);
            }

            snakeGround[i] = arr;

        }

        int foodLeftToEat = 10;
        while(foodLeftToEat > 0) {

            String command = sc.nextLine();

            move(snakeGround,snakePos, command);

            if (snakeOutOfGround(snakePos,n)) {
                break;
            }

            snakeOnLair(snakeGround, snakePos, lairPos);
            foodLeftToEat -= snakeOnFood(snakeGround, snakePos);
        }

        if (foodLeftToEat > 0) {
            System.out.println("Game over!");
        } else {
            System.out.println("You won! You fed the snake.");
        }

        System.out.printf("Food eaten: %d",(10 - foodLeftToEat));

        for (int i = 0; i < snakeGround.length; i++) {
            System.out.println();
            for (int j = 0; j < snakeGround[0].length; j++) {
                System.out.printf("%s",snakeGround[i][j]);
            }

        }
    }

    private static int snakeOnFood(String[][] snakeGround, int[] snakePos) {

        if (snakeGround[snakePos[0]][snakePos[1]].equals(snakeFood)) {

            snakeGround[snakePos[0]][snakePos[1]] = "S";
            return 1;
        }

        return 0;
    }

    private static void snakeOnLair(String[][] snakeGround, int[] snakePos, int[] lairPos) {

        if (snakePos[0] == lairPos[0] && snakePos[1] == lairPos[1]) {

            snakeGround[snakePos[0]][snakePos[1]] = snakeTrail;

            snakePos[0] = lairPos[2];
            snakePos[1] = lairPos[3];

            snakeGround[snakePos[0]][snakePos[1]] = "S";

        } else if (snakePos[0] == lairPos[2] && snakePos[1] == lairPos[3]) {
            snakeGround[snakePos[0]][snakePos[1]] = snakeTrail;

            snakePos[0] = lairPos[0];
            snakePos[1] = lairPos[1];

            snakeGround[snakePos[0]][snakePos[1]] = "S";

            lairPos[2] = -1;
            lairPos[3] = -1;
            lairPos[0] = -1;
            lairPos[1] = -1;
        }
    }

    private static void move(String[][] snakeGround,int[] snakePos, String command) {

        int snakePositionRow = snakePos[0];
        int snakePositionColumn = snakePos[1];

        switch(command) {

            case "up":
                snakePositionRow--;
                break;
            case "down":
                snakePositionRow++;
                break;
            case "left":
                snakePositionColumn--;
                break;
            case "right":
                snakePositionColumn++;
                break;
        }

        snakeGround[snakePos[0]][snakePos[1]] = snakeTrail;

        snakePos[0] = snakePositionRow;
        snakePos[1] = snakePositionColumn;

    }

    private static boolean snakeOutOfGround(int[] snakePos, int snakeGroundSize) {

        if (snakePos[0] <0 || snakePos[0] >= snakeGroundSize
            || snakePos[1] < 0 || snakePos[1] >= snakeGroundSize) {

            return true;
        }

        return false;

    }

    private static int findLair(String[] arr, int[] lairPos, int currentRow) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals("B")) {
                if (lairPos[0] == -1) {
                    lairPos[0] = currentRow;
                    lairPos[1] = i;
                } else {
                    lairPos[2] = currentRow;
                    lairPos[3] = i;
                }
                return 1;
            }
        }

        return 0;
    }

    private static boolean findSnakePos(String[] arr, int[] snakePos, int currentRow) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals("S")) {
                snakePos[0] = currentRow;
                snakePos[1] = i;
                return true;
            }
        }

        return false;
    }
}
