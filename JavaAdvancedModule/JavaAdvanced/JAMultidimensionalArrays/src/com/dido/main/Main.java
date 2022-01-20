package com.dido.main;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
	// write your code here

//        printReverseMatrix();
//        shuffleMatrixByOne();

//        rotateMatrixBy90();
//        rotateMatrixBy180();

//        rotateMatrixBy270();
//        encryptMatrix();
//        decryptMatrix();

//        hashedItBig();
//        printMatrixTranspose();

//        populateMatrixInSeq();

//        nukeTheMatrix();

//        makeHappyMatrix();
//        makeSadMatrix();
//        canSterlingScoreAGoal();

//        enterMatrixElement();

//        findMatrixElementDistance();

//        reverseMatrixDiagonals();

//        playBunnyFair();

//        kaneVsSon();


//        hitAndGo();

//        scoreOrMiss();
    }

    public static void scoreOrMiss() {

        Scanner sc = new Scanner(System.in);

        int[][] kaneScore = new int[6][6];
        int[][] kaneMiss = new int[6][6];

        String input = sc.nextLine();

        // Score idx1 idx2, Miss idx1 idx2
        boolean invalidIndex = false;
        while(!input.equals("end")) {

            String[] scoreData = input.split("\\s");

            String type = scoreData[0];
            switch(type) {

                case "Score":
                    int idxOne = Integer.parseInt(scoreData[1]);
                    int idxTwo = Integer.parseInt(scoreData[2]);

                    if (idxOne < 0 || idxOne >= kaneScore.length
                            || idxTwo < 0 || idxTwo >= kaneScore.length) {
                        System.out.println("Invalid index, try another one.");
                        break;
                    }
                    kaneScore[idxOne][idxTwo]++;
                    break;
                case "Miss":
                    idxOne = Integer.parseInt(scoreData[1]);
                    idxTwo = Integer.parseInt(scoreData[2]);

                    if (idxOne < 0 || idxOne >= kaneScore.length
                            || idxTwo < 0 || idxTwo >= kaneScore.length) {
                        System.out.println("Invalid index, try another one.");
                        break;
                    }
                    kaneMiss[idxOne][idxTwo]++;
                    break;

                default:
                    break;
            }

            input = sc.nextLine();
        }

        System.out.println("###Kane score###");
        printMatrix(kaneScore);
        System.out.println("###Kane misses###");
        printMatrix(kaneMiss);

        int totalScore = calculateMatrixSum(kaneScore);
        int totalMiss = calculateMatrixSum(kaneMiss);

        if (totalScore > totalMiss) {
            System.out.println("Kane score is: "+totalScore);
        } else {
            System.out.println("Kane misses are: "+totalMiss);
        }
    }

    public static int calculateMatrixSum(int[][] matrix) {

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                sum += matrix[i][j];
            }
        }

        return sum;
    }

    public static void hitAndGo() {

        Scanner sc = new Scanner(System.in);
        int[][] matrix = populateRandomMatrix();

        printMatrix(matrix);
        int[] hitOne = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int[] hitTwo = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        if (matrix[hitOne[0]][hitOne[1]] > matrix[hitTwo[0]][hitTwo[1]]) {

            System.out.println("Player one wins");
        } else if(matrix[hitOne[0]][hitOne[1]] < matrix[hitTwo[0]][hitTwo[1]]) {

            System.out.println("Player two wins");
        } else {
            System.out.println("Draw");
        }
    }

    public static void kaneVsSon() {

        int[][] matrixKane = populateRandomMatrix();
        int[][] matrixSon = populateRandomMatrix();

        // calculate ga inv
        int kanePower = 0;
        int sonPower = 0;


        for (int i = 0; i < matrixKane.length; i++) {

            for (int j = 0; j < matrixKane[i].length; j++) {

                int powerMultiplier = -1;
                if ((i+1) % 2 != 0) {
                    // goals
                    powerMultiplier = 4;
                } else {
                    // assists
                    powerMultiplier = 3;
                }

                kanePower += (matrixKane[i][j] * powerMultiplier);
                sonPower += (matrixSon[i][j] * powerMultiplier);
            }
        }

        if (kanePower > sonPower) {
            System.out.println("Kane won: "+kanePower);
            printMatrix(matrixKane);
        } else if (sonPower > kanePower) {
            System.out.println("Son won: "+sonPower);
            printMatrix(matrixSon);
        } else {
            System.out.println("Kane and Son drew: "+sonPower);
        }
    }

    public static int[][] populateRandomMatrix() {

        int[][] result = new int[6][6];

        Random rand = new Random();
        for (int i = 0; i < result.length; i++) {

            for (int j = 0; j < result[i].length; j++) {

                // 3 goals or assists max
                result[i][j] = rand.nextInt(3);
            }
        }

        return result;
    }

    public static void playBunnyFair() {

        String[][] bunnyInfo = readBunnyInfo();

        int[] playerCoordinates = findPlayerCoordinates(bunnyInfo);

        if (playerCoordinates != null) {
            System.out.println();
            Arrays.stream(playerCoordinates)
                    .mapToObj(d -> String.format("%d ", d))
                    .forEach(System.out::print);
        }

        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();

        for (int i = 0; i < command.length(); i++) {

            char ch = command.charAt(i);

            movePlayer(bunnyInfo, ch, playerCoordinates);

        }

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

    public static void movePlayer(String[][] bunnyInfo, char c, int[] playerCoordinates) {

        int[] currentPlayerCoordinates = playerCoordinates;

        int rowPosition = -1;
        int colPosition = -1;
        switch(c) {

            case 'U':

                rowPosition = --currentPlayerCoordinates[0];
                colPosition = currentPlayerCoordinates[1];
                if (rowPosition < 0) {
                    // out of bounds and won
                    return;
                }


                break;
            case 'D':
                // dsfsf
                rowPosition = ++currentPlayerCoordinates[0];
                colPosition = currentPlayerCoordinates[1];
                if (rowPosition >= bunnyInfo.length) {
                    // out of bounds and won
                    return;
                }

                break;
            case 'L':
                // fdafa
                rowPosition = currentPlayerCoordinates[0];
                colPosition = --currentPlayerCoordinates[1];
                if (colPosition < 0) {
                    // out of bounds and won
                    return;
                }

                break;
            case 'R':
                // aswqqe
                rowPosition = currentPlayerCoordinates[0];
                colPosition = ++currentPlayerCoordinates[1];
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

    public static String[][] readBunnyInfo() {

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

        Arrays.stream(bunnyLair[0]).forEach(System.out::print);

        return bunnyLair;
    }

    public static void reverseMatrixDiagonals() {

        Scanner sc = new Scanner(System.in);

        String[] size = sc.nextLine().split("\\s");

        int r = Integer.parseInt(size[0]);
        int c = Integer.parseInt(size[1]);

        int[][] matrix = new int[r][];

        for (int i = 0; i < r; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .limit(c).mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        // foreach element starting from last on last row
        // check up and forward and then down and backward
        // until limit is reached - row or column

        for (int i = matrix[r-1].length-1; i >=0 ; i--) {

            // print first
            System.out.printf("%d ",matrix[r-1][i]);

            // look for others
            int rowUp = r-2;
            int colUp = i + 1;

            while(true) {

                if (rowUp < 0 || colUp >= matrix[rowUp].length) {
                    break;
                }

                System.out.printf("%d ",matrix[rowUp][colUp]);
                rowUp--;
                colUp++;
            }

            System.out.println();


        }

        for (int i = 0; i < r-1; i++) {
            // move up on last bit
            int rowUp = r-2-i;
            int colUp = 0;

            while(true) {
                if (rowUp < 0 || colUp >= matrix[rowUp].length) {
                    break;
                }

                System.out.printf("%d ",matrix[rowUp][colUp]);
                rowUp--;
                colUp++;
            }

            System.out.println();
        }


    }

    public static void findMatrixElementDistance() {
        // should be improved with left and right steps ...
        Scanner sc = new Scanner(System.in);

        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());

        int[][] matrix = populateMatrixInSeqV2(r, c);

        int entryRowIdx = Integer.parseInt(sc.nextLine());

        int targetRow = Integer.parseInt(sc.nextLine());
        int targetCol = Integer.parseInt(sc.nextLine());

        int countDistance = 0;
        boolean found = false;
        if (matrix[targetRow][targetCol] != -1) {

            // free, get count of steps
            countDistance += Math.abs(targetRow - entryRowIdx);
            countDistance += targetCol;
            matrix[targetRow][targetCol] = -1;
            found = true;
        } else {

            // check left and right columns
            int moveLeft = targetCol+1;
            int moveRight = targetCol-1;

            while(true) {

                if (moveLeft >=0 && matrix[targetRow][moveLeft] != -1) {
                    countDistance--;
                    found = true;
                    matrix[targetRow][moveLeft] = -1;
                    break;
                }

                if (moveRight < matrix[0].length && matrix[targetRow][moveRight] != -1) {

                    countDistance++;
                    found = true;
                    matrix[targetRow][moveRight] = -1;
                    break;
                }

                moveRight++;
                moveLeft--;

            }

        }

        if (found) {
            System.out.println(countDistance);
        }

    }

    public static void enterMatrixElement() {

        Scanner sc = new Scanner(System.in);

        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());

        int[][] matrix = populateMatrixInSeqV2(r, c);

        int indexRow = Integer.parseInt(sc.nextLine());
        int indexCol = Integer.parseInt(sc.nextLine());

        if (indexRow < 0 || indexRow >= matrix.length
                || indexCol < 0 || indexCol >= matrix[0].length) {

            return;
        }

        System.out.printf("%d ", matrix[indexRow][indexCol]);

    }

    public static void canSterlingScoreAGoal() {

        Scanner sc = new Scanner(System.in);

        String[] sizes = sc.nextLine().split("\\s");

        int[] indexes = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();
        int row = Integer.parseInt(sizes[0]);
        int column = Integer.parseInt(sizes[1]);

        int[][] matrix = getMatrix(row, column);

        printMatrix(matrix);

        if (indexes[0] < 0 || indexes[1] >= matrix.length || indexes[1]<0 || indexes[1] >= matrix.length) {

            return;

        }

        if (matrix[indexes[0]][indexes[1]] >= 1) {

            System.out.printf("Yes Sterling scores %d goals.%n",matrix[indexes[0]][indexes[1]]);
        } else {
            System.out.println("No Sterling cannot score a goal.");
        }
    }

    public static int[][] getMatrix(int row, int col) {

        int[][] matrix = new int[row][col];

        Random rand  =  new Random();
        int num = rand.nextInt(5);

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                matrix[i][j] = num;
                num = rand.nextInt(5);

            }
        }

        return matrix;
    }

    public static void nukeTheMatrix() {

        Scanner sc = new Scanner(System.in);
        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        int[] nukeData = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int[][] matrix = populateMatrixInSeqV2(r,c);

        int x = nukeData[0];
        int y = nukeData[1];
        int rad = nukeData[2];

        // nuke the x,y elem
        matrix[x][y] = -1;
        int currentY = y;
        // nuke to the right radius len
        int countSteps = rad;
        while(--currentY > 0) {

            matrix[x][currentY] = -1;
            countSteps--;
            if (countSteps == 0) {
                break;
            }
        }

        currentY = y;
        countSteps = rad;
        // nuke to the left radius len
        while(++currentY < matrix[x].length) {

            matrix[x][currentY] = -1;
            countSteps--;
            if (countSteps == 0) {
                break;
            }
        }

        // nuke up radius len
        int currentX = x;
        countSteps = rad;
        while(--currentX > 0) {

            matrix[currentX][y] = -1;
            countSteps--;
            if (countSteps == 0) {
                break;
            }
        }

        // nuke down radius len
        currentX = x;
        countSteps = rad;
        while(++currentX < matrix.length) {

            matrix[currentX][y] = -1;
            countSteps--;
            if (countSteps == 0) {
                break;
            }
        }

//        printMatrix(matrix);

//        printNukedMatrix(matrix);

    }

    public static void makeSadMatrix() {
        System.out.println("Sad matrix ###");
        String[][] matrixHappy = new String[5][5];

        for (int i = 0; i < matrixHappy.length; i++) {

            for (int j = 0; j < matrixHappy[i].length; j++) {

                matrixHappy[i][j] = ":(";

                System.out.printf("%s ",matrixHappy[i][j]);
            }

            System.out.println();
        }

    }

    public static void makeHappyMatrix() {
        System.out.println("Happy matrix ###");
        String[][] matrixHappy = new String[5][5];

        for (int i = 0; i < matrixHappy.length; i++) {

            for (int j = 0; j < matrixHappy[i].length; j++) {

                matrixHappy[i][j] = ":)";

                System.out.printf("%s ",matrixHappy[i][j]);
            }

            System.out.println();
        }

    }

    public static void printNukedMatrix(int[][] matrix) {

        System.out.println("### Nuked matrix ###");
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                // print only not nuked elements otherwise just spaces
                if (matrix[i][j] != -1) {
                    System.out.printf("%d ", matrix[i][j]);
                } else {
                    System.out.printf("  ");
                }
            }

            System.out.println();
        }
    }

    public static int[][] populateMatrixInSeqV2(int r, int c) {

        int[][] matrix = new int[r][c];

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {

                matrix[i][j] = i * c + j + 1;
                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }

        return matrix;

    }

    public static void populateMatrixInSeq() {

        Scanner sc = new Scanner(System.in);

        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[r][c];

        for (int i = 0; i < r; i++) {

            for (int j = 0; j < c; j++) {

                matrix[i][j] = i * c + j + 1;
                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }


    }

    public static void printMatrixTranspose() {

        int[][] matrix = new int[2][2];

        populateMatrix(matrix);

        for (int i = 0; i < matrix[0].length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                System.out.printf("%d ",matrix[j][i]);
            }

            System.out.println();
        }
    }

    public static void hashedItBig() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, Integer> hashedIt = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();

            hashedIt.putIfAbsent(word,i+1);
        }

        hashedIt.entrySet().stream().map(h -> String.format("%s -> %d",h.getKey(), h.getValue()))
                .forEach(System.out::println);
    }

    public static void test2(char[] arr) {

        char[] arr2 = new char[3];
        arr2[0] = '3';
        arr2[1] = '5';
        arr[0] = arr2[1];
    }



    public static void test(char[][] arr) {

        char[][] arr2 = new char[3][2];
        arr2[0][0] = '3';
        arr2[1][0] = '5';
        arr[0] = arr2[0];
    }

    public static void decryptMatrix() {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc, n);

        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                matrix[i][j] = (matrix[i][j] / b) - a;

                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }

    }

    public static void encryptMatrix() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc, n);

        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                matrix[i][j] = (matrix[i][j] + a) * b;

                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }

    }

    public static void rotateMatrixBy270() {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc, n);

        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                rotatedMatrix[j][i] = matrix[i][matrix[0].length - 1 - j];
            }
        }

        printMatrix(rotatedMatrix);
    }

    public static void rotateMatrixBy180() {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc, n);

        int[][] rotatedMatrix = new int[matrix.length][matrix[0].length];

        for (int i = matrix.length - 1; i >=0; i--) {

            for (int j = matrix[i].length-1; j >=0; j--) {

                rotatedMatrix[matrix.length - 1 - i][matrix.length - 1 - j] = matrix[i][j];
            }
        }

        printMatrix(rotatedMatrix);

    }

    public static void rotateMatrixBy90() {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = readMatrix(sc, n);

        int[][] rotatedMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {

            for (int j = 0; j < matrix.length; j++) {

                rotatedMatrix[i][j] = matrix[matrix.length-1-j][i];
            }
        }

        for (int i = 0; i < rotatedMatrix.length; i++) {

            for (int j = 0; j < rotatedMatrix[i].length; j++) {

                System.out.printf("%d ",rotatedMatrix[i][j]);
            }

            System.out.println();
        }

    }

    public static void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[i].length; j++) {

                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }
    }

    public static int[][] readMatrix(Scanner sc, int n) {

        int[][] matrix =  new int[n][];
        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        return matrix;
    }

    public static void shuffleMatrixByOne() {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[n][];
        for (int i = 0; i < n; i++) {

            int[] arr = Arrays.stream(sc.nextLine().split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            matrix[i] = arr;
        }

        for (int i = 0; i < n-1; i++) {

            for (int j = 0; j < n; j++) {

                int temp = matrix[i][j];
                matrix[i][j] = matrix[i+1][j];
                matrix[i+1][j] = temp;
            }
        }

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                System.out.printf("%d ",matrix[i][j]);
            }

            System.out.println();
        }
    }

    public static void printReverseMatrix() {

        int[][] matrix = new int[2][2];

        populateMatrix(matrix);

        for (int i = matrix.length-1; i >=0 ; i--) {

            for (int j = matrix[i].length-1; j >=0 ; j--) {

                System.out.println(matrix[i][j]);
            }
        }

    }

    public static void populateMatrix(int[][] matrix) {
        matrix[0][0] = 1;
        matrix[0][1] = 2;
        matrix[1][0] = 3;
        matrix[1][1] = 4;

    }
}
