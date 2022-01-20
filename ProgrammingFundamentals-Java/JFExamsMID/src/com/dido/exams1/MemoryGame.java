package com.dido.exams1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MemoryGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> elements = Arrays.stream(sc.nextLine().split("\\s"))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        int moves = 0;
        while(!input.equals("end")) {

            int[] indexes = Arrays.stream(input.split("\\s"))
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();

            moves++;

            if (!checkIndexes(indexes, elements)) {
                System.out.println("Invalid input! Adding additional elements to the board");

                int midIndex = elements.size() / 2;
                elements.add(midIndex, "-"+moves+"a");
                elements.add(midIndex, "-"+moves+"a");
            } else {
                String elementOne = elements.get(indexes[0]);
                String elementTwo = elements.get(indexes[1]);

                if (elementOne.equals(elementTwo)) {

                    System.out.printf("Congrats! You have found matching elements - %s!%n",elementTwo);
                    elements.remove(elementOne);
                    elements.remove(elementTwo);
                } else {
                    System.out.println("Try again!");
                }

            }

            if (elements.isEmpty()) {
                System.out.printf("You have won in %d turns!", moves);
                break;
            }

            input = sc.nextLine();
        }

        if (!elements.isEmpty()) {
            System.out.println("Sorry you lose :(");

            elements.stream().map(s -> String.format("%s ",s))
                    .forEach(System.out::print);
        }
    }

    public static boolean checkIndexes(int[] indexes, List<String> elements) {

        if (indexes[0] == indexes[1]) {
            // cheating
            return false;
        } else if (indexes[0] < 0 || indexes[0] >= elements.size() || indexes[1] < 0
                || indexes[1] >= elements.size()) {
            return false;
        }

        return true;
    }
}
