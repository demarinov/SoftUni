package com.dido.exercise;

import java.util.Scanner;

public class ElKamino3 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int num = Integer.parseInt(scanner.nextLine());

            String input = scanner.nextLine();
            int positionMAx = num - 1;
            int maxSum = 0;
            int lengthMax = 0;
            int[] winInt = new int[num];
            int counter = 0;
            int counterWin = 0;
            while (!(input.equals("Clone them!"))) {
                counter++;
                int[] inputInt = new int[num];
                String[] inputArr = input.split("!+");
                for (int i = 0; i < num; i++) {
                    inputInt[i] = Integer.parseInt(inputArr[i]);
                }

                int sum = 0;
                int curStart = -1;
                int curLength = 0;
                int currentPositionMax = -1;
                int currentLengthMax = 0;
                for (int i = 0; i < inputInt.length; i++) {
                    if (inputInt[i] == 1) {
                        if (curStart == -1) {
                            curStart = i;
                        }
                        sum++;
                        curLength++;
                    }
                    if (inputInt[i] == 0 || i == inputInt.length - 1) {
                        if (curLength > currentLengthMax) {
                            currentLengthMax = curLength;
                            currentPositionMax = curStart;
                        }
                        curLength = 0;
                        curStart = -1;
                    }
                }

                boolean flagWin = false;

                if (currentLengthMax > lengthMax) {
                    flagWin = true;
                }
                if (currentLengthMax == lengthMax && currentPositionMax < positionMAx) {
                    flagWin = true;
                }
                if (currentLengthMax == lengthMax && currentPositionMax == positionMAx && sum > maxSum) {
                    flagWin = true;

                }
                if (flagWin) {
                    lengthMax = currentLengthMax;
                    positionMAx = currentPositionMax;
                    winInt = inputInt;
                    maxSum = sum;
                    counterWin = counter;
                }
                input = scanner.nextLine();
            }


            System.out.printf("Best DNA sample %d with sum: %d.%n", counterWin, maxSum);
            for (int i = 0; i < num; i++) {

                System.out.print(winInt[i] + " ");
            }
        }
}
