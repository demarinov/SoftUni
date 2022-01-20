package com.dido.exams2;

import java.util.Scanner;

public class CounterStrike {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int initialEnergy = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();

        int leftEnergy = initialEnergy;
        int countWon = 0;
        boolean enoughEnergy = true;
        while(!input.equals("End of battle")) {

            int energy = Integer.parseInt(input);

            int energyDiff = leftEnergy - energy;
            if (energyDiff < 0) {
                enoughEnergy = false;
                break;
            }

            countWon++;

            if (countWon % 3  == 0) {
                leftEnergy +=countWon;
            }

            leftEnergy -= energy;
            input = sc.nextLine();
        }

        if (enoughEnergy) {
            System.out.printf("Won battles: %d. Energy left: %d", countWon, leftEnergy);
        } else {
            System.out.printf("Not enough energy! Game ends with " +
                    "%d won battles and %d energy",countWon,leftEnergy);
        }
    }
}
