package com.dido.exams1;

import java.util.Arrays;
import java.util.Scanner;

public class TheLift {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int peopleWaiting = Integer.parseInt(sc.nextLine());

        int[] wagons = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        for (int i = 0; i < wagons.length; i++) {

            if (wagons[i] < 4) {

                wagons[i] += 1;
                peopleWaiting--;
                i--;
            }

            if (peopleWaiting == 0) {
                break;
            }
        }

        if (peopleWaiting == 0 && !wagonsFull(wagons)) {
            System.out.println("The lift has empty spots!");
            printWagons(wagons);
        } else if (peopleWaiting > 0 && wagonsFull(wagons)) {
            System.out.printf("There isn't enough space! %d people in a queue!%n",peopleWaiting);
            printWagons(wagons);
        } else if (peopleWaiting == 0 && wagonsFull(wagons)) {
            printWagons(wagons);
        }
    }

    public static void printWagons(int[] wagons) {

        for (int i = 0; i < wagons.length; i++) {

            System.out.printf("%d ",wagons[i]);
        }
    }

    public static boolean wagonsFull(int[] wagons) {

        for (int i = 0; i < wagons.length; i++) {

            if (wagons[i] != 4) {

                return false;
            }
        }

        return true;
    }
}
