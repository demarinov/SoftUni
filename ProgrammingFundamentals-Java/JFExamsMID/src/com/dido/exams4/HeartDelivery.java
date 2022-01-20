package com.dido.exams4;

import java.util.Arrays;
import java.util.Scanner;

public class HeartDelivery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] hearts = Arrays.stream(sc.nextLine().split("@"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        String input = sc.nextLine();

        int lastPosition = 0;
        int countHousesWithValentine = 0;
        while(!input.equals("Love!")) {

            String[] commandData = input.split("\\s");

            if (commandData[0].equals("Jump")) {
                int length = Integer.parseInt(commandData[1]);
                int currentPos = lastPosition + length;

                if (hearts.length-1 < currentPos) {
                    // go at start
                    if (hearts[0] != 0) {
                        hearts[0]-=2;
                        if (hearts[0] == 0) {
                            System.out.printf("Place %d has Valentine's day.%n", 0);
                            countHousesWithValentine++;
                        }
                    } else {
                        System.out.printf("Place %d already had Valentine's day.%n",0);
                    }

                    lastPosition = 0;

                } else {
                    // go at index
                    if (hearts[currentPos] != 0) {
                        hearts[currentPos]-=2;
                        if (hearts[currentPos] == 0) {
                            System.out.printf("Place %d has Valentine's day.%n", currentPos);
                            countHousesWithValentine++;
                        }

                    } else {
                        System.out.printf("Place %d already had Valentine's day.%n",currentPos);
                    }

                    lastPosition = currentPos;
                }
            }

            input = sc.nextLine();
        }

        System.out.printf("Cupid's last position was %d.%n",lastPosition);

        if (hearts.length == countHousesWithValentine) {
            System.out.println("Mission was successful.");
        } else {
            System.out.printf("Cupid has failed %d places.",(hearts.length - countHousesWithValentine));
        }

    }
}
