package com.dido.exams6;

import java.util.Scanner;
// weapon particles
public class Problem2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] weaponParts = sc.nextLine().split("\\|");

        String input = sc.nextLine();

        while(!input.equals("Done")) {

            String[] commandData = input.split("\\s");

            String command = commandData[0] + " "+commandData[1];

            switch(command) {

                case "Move Left":
                    int index = Integer.parseInt(commandData[2]);

                    if (index-1<0 || index < 0 || index >= weaponParts.length) {

                        input = sc.nextLine();
                        continue;
                    }

                    String temp = weaponParts[index];
                    weaponParts[index] = weaponParts[index - 1];
                    weaponParts[index - 1] = temp;
                    break;
                case "Move Right":
                    index = Integer.parseInt(commandData[2]);

                    if (index+1>= weaponParts.length || index < 0 || index >= weaponParts.length) {

                        input = sc.nextLine();
                        continue;
                    }
                    temp = weaponParts[index];
                    weaponParts[index] = weaponParts[index + 1];
                    weaponParts[index + 1] = temp;

                    break;
                case "Check Odd":
                    for (int i = 0; i < weaponParts.length; i++) {

                        if (i % 2 != 0) {
                            System.out.printf("%s ",weaponParts[i]);
                        }
                    }
                    System.out.println();
                    break;
                case "Check Even":
                    for (int i = 0; i < weaponParts.length; i++) {

                        if (i % 2 == 0) {
                            System.out.printf("%s ",weaponParts[i]);
                        }
                    }
                    System.out.println();
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        System.out.printf("You crafted %s!",String.join("",weaponParts));
    }
}
