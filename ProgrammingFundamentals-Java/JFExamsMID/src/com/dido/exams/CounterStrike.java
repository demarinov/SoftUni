package com.dido.exams;

import java.util.Scanner;

public class CounterStrike {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int initialEnergy = Integer.parseInt(sc.nextLine());
        int currentEnergy = initialEnergy;
        int countWins = 0;
        boolean allEnergyLost = false;

        if (currentEnergy <= 0 || currentEnergy > 10000) {
            allEnergyLost = true;
        } else {
            String command = sc.nextLine();

            while (!command.equalsIgnoreCase("end of battle")) {

                int energyDelta = Integer.parseInt(command);

                if (energyDelta <= 0 || energyDelta > 10000) {
                    command = sc.nextLine();
                    continue;
                }

                int energyLeft = currentEnergy - energyDelta;

                if (energyLeft < 0) {
                    allEnergyLost = true;
                    break;
                }

                currentEnergy = energyLeft;
                countWins++;

                if (countWins % 3 == 0) {
                    currentEnergy += countWins;
                }

                command = sc.nextLine();
            }
        }

        if (allEnergyLost) {
            System.out.println("Not enough energy! Game ends with "+countWins+" won battles and "+currentEnergy+" energy");
        } else {
            System.out.println("Won battles: "+countWins+". Energy left: "+currentEnergy);
        }



    }
}
