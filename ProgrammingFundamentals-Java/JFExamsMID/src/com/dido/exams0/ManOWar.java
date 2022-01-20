package com.dido.exams0;

import java.util.Arrays;
import java.util.Scanner;

public class ManOWar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] pirateShipStatus = Arrays.stream(sc.nextLine().split(">"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int[] warshipStatus = Arrays.stream(sc.nextLine().split(">"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int healthCapacityMax = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();

        while (!input.equals("Retire")) {

            String[] commandData = input.split("\\s");

            String command = commandData[0];
            switch (command) {

                case "Fire":
                    int index = Integer.parseInt(commandData[1]);
                    int damage = Integer.parseInt(commandData[2]);

                    if (index < 0 || index >= warshipStatus.length) {
                        input = sc.nextLine();
                        continue;
                    }

                    warshipStatus[index] -= damage;

                    if (warshipStatus[index] <= 0) {
                        System.out.printf("You won! The enemy ship has sunken.%n");
                        return;
                    }

                    break;
                case "Defend":
                    int startIndex = Integer.parseInt(commandData[1]);
                    int endIndex = Integer.parseInt(commandData[2]);
                    damage = Integer.parseInt(commandData[3]);

                    if (startIndex < 0 || startIndex >= pirateShipStatus.length
                            || endIndex < 0 || endIndex >= pirateShipStatus.length
                            || startIndex > endIndex) {
                        input = sc.nextLine();
                        continue;
                    }

                    for (int i = startIndex; i <= endIndex; i++) {

                        pirateShipStatus[i] -= damage;

                        if (pirateShipStatus[i] <= 0) {
                            System.out.printf("You lost! The pirate ship has sunken.%n");
                            return;
                        }
                    }
                    break;
                case "Repair":
                    index = Integer.parseInt(commandData[1]);
                    int health = Integer.parseInt(commandData[2]);
                    if (index < 0 || index >= pirateShipStatus.length) {
                        input = sc.nextLine();
                        continue;
                    }

                    pirateShipStatus[index] += health;

                    if (pirateShipStatus[index] > healthCapacityMax) {
                        pirateShipStatus[index] = healthCapacityMax;
                    }

                    break;
                case "Status":

                    double repairCapacity = 0.2 * healthCapacityMax;

                    int count = 0;

                    for (int i = 0; i < pirateShipStatus.length; i++) {

                        if (pirateShipStatus[i] < repairCapacity) {
                            count++;
                        }
                    }

                    System.out.printf("%d sections need repair.%n", count);
                    break;

                default:
                    break;

            }

            input = sc.nextLine();
        }

        System.out.printf("Pirate ship status: %d%n", Arrays.stream(pirateShipStatus).sum());
        System.out.printf("Warship status: %d", Arrays.stream(warshipStatus).sum());
    }
}
