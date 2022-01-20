package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class LadyBugs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int fieldSize = Integer.parseInt(sc.nextLine());

        if (fieldSize > 0 && fieldSize <= 1000) {

            int[] indexes = Arrays.stream(sc.nextLine().split(" "))
                    .mapToInt(s -> Integer.parseInt(s)).toArray();
            int[] cells = new int[fieldSize];

            // populate initial ladybug indexes.
            for (int i = 0; i < indexes.length; i++) {

                if (indexes[i] < 0 || indexes[i] >= fieldSize) {
                    continue;
                }
                cells[indexes[i]] = 1;
            }

            String input = sc.nextLine();
            int commandCount = 0;

            while (!input.equalsIgnoreCase("end")) {

                String[] command = input.split(" ");
                commandCount++;

                int bugIdx = Integer.parseInt(command[0]);
                String direction = command[1];
                int flyLen = Integer.parseInt(command[2]);

                if (bugIdx < 0 || bugIdx >= cells.length || cells[bugIdx] != 1) {
                    input = sc.nextLine();
                    continue;
                }


                // simulate bug flying
                if (cells[bugIdx] == 1 && (bugIdx >= 0 && bugIdx < cells.length)) {
                    // started moving
                    cells[bugIdx] = 0;

                    if (direction.equalsIgnoreCase("right")) {

                        int nextBugIdx = bugIdx + flyLen;
                        while (nextBugIdx < cells.length) {

                            if (nextBugIdx < 0) {
                                break;
                            }

                            // already ladybug there
                            if (cells[nextBugIdx] == 1) {
                                nextBugIdx += flyLen;
                            } else {
                                cells[nextBugIdx] = 1;
                                break;
                            }


                        }


                    } else if (direction.equalsIgnoreCase("left")) {
                        int nextBugIdx = bugIdx - flyLen;
                        while (nextBugIdx >= 0) {

                            if (nextBugIdx >= cells.length) {
                                break;
                            }

                            // already ladybug there
                            if (cells[nextBugIdx] == 1) {
                                nextBugIdx -= flyLen;
                            } else {
                                cells[nextBugIdx] = 1;
                                break;
                            }


                        }

                    }
                }


                if (commandCount == 100) {
                    break;
                }
                input = sc.nextLine();
            }


            for (int i = 0; i < cells.length; i++) {

                System.out.printf("%d ", cells[i]);
            }


        }

    }
}
