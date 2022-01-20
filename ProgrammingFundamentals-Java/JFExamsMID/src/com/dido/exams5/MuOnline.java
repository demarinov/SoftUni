package com.dido.exams5;

import java.util.Arrays;
import java.util.Scanner;

public class MuOnline {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] dungeonRooms = sc.nextLine().split("\\|");

        int initialHealth = 100;
        int initialBitcoins = 0;

        int currentHealth = initialHealth;
        int currentBitcoins = initialBitcoins;

        boolean alive = true;
        int bestRoom = 0;
        for (int i = 0; i < dungeonRooms.length; i++) {

            String[] commandData = dungeonRooms[i].split("\\s");

            String command = commandData[0];

            switch (command) {

                case "potion":

                    int healthGain = Integer.parseInt(commandData[1]);


                    if (currentHealth + healthGain <= 100) {
                        currentHealth += healthGain;
                        System.out.printf("You healed for %d hp.%n",healthGain);
                    } else {
                        System.out.printf("You healed for %d hp.%n",(100 - currentHealth));
                        currentHealth += (100 - currentHealth);
                    }

                    System.out.printf("Current health: %d hp.%n",currentHealth);
                    bestRoom++;
                    break;
                case "chest":
                    int bitcoinGain = Integer.parseInt(commandData[1]);
                    System.out.printf("You found %d bitcoins.%n",bitcoinGain);
                    currentBitcoins += bitcoinGain;
                    bestRoom++;
                    break;
                default:
                    // attack
                    int monsterAttack = Integer.parseInt(commandData[1]);

                    currentHealth -= monsterAttack;

                    if (currentHealth <= 0) {

                        alive = false;
                        System.out.printf("You died! Killed by %s.%n",command);
                    } else {
                        System.out.printf("You slayed %s.%n",command);
                    }
                    bestRoom++;
                    break;
            }

            if (!alive) {
                // print best room
                System.out.printf("Best room: %d%n",bestRoom);
                break;
            }
        }

        if (alive) {

            // or on new lines ...
//            System.out.printf("You've made it!, Bitcoins: %d, Health: %d",currentBitcoins, currentHealth);
            System.out.println("You've made it!");
            System.out.printf("Bitcoins: %d%n",currentBitcoins);
            System.out.printf("Health: %d",currentHealth);
        }

    }
}
