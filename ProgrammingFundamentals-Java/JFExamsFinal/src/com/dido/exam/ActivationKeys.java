package com.dido.exam;

import java.util.Scanner;

public class ActivationKeys {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rawActivationKey = sc.nextLine();

        String command = sc.nextLine();

        String activationKey = rawActivationKey;

        while(!command.equalsIgnoreCase("generate")) {
            String[] commandData = command.split(">>>");

            String instruction = commandData[0];
            if (instruction.equalsIgnoreCase("contains")) {

//                •	Contains>>>{substring} – checks if the raw activation key contains the given substring.
//                o	If it does prints: "{raw activation key} contains {substring}".
//                        o	If not, prints: "Substring not found!"
                String containedString = commandData[1];

                if (activationKey.contains(containedString)) {
                    System.out.println(activationKey+" contains "+containedString);
                } else {
                    System.out.println("Substring not found!");
                }

            } else if (instruction.equalsIgnoreCase("flip")) {

//                •	Flip>>>Upper/Lower>>>{startIndex}>>>{endIndex}
//                o	Changes the substring between the given indices (the end index is exclusive) to upper or lower case.
//                    o	All given indexes will be valid.
//                            o	Prints the activation key.
                 String caseStyle = commandData[1];
                 int startIndex = Integer.parseInt(commandData[2]);
                 int endIndex = Integer.parseInt(commandData[3]);

                 activationKey = flip(activationKey, caseStyle, startIndex, endIndex);
                System.out.println(activationKey);
            } else if (instruction.equalsIgnoreCase("slice")) {
//•	Slice>>>{startIndex}>>>{endIndex}
//            o	Deletes the characters between the start and end indices (end index is exclusive).
//            o	Both indices will be valid.
//            o	Prints the activation key.

                int startIndex = Integer.parseInt(commandData[1]);
                int endIndex = Integer.parseInt(commandData[2]);

                activationKey = slice(activationKey, startIndex, endIndex);
                System.out.println(activationKey);
            }

            command = sc.nextLine();
        }

        System.out.println("Your activation key is: "+activationKey);
    }

    public static String slice(String rawKey, int startIndex, int endIndex) {

        String result = "";

        result += rawKey.substring(0, startIndex);
        result += rawKey.substring(endIndex);

        return result;

    }

    public static String flip(String rawKey, String caseStyle, int startIndex, int endIndex) {

        String result = "";

        result += rawKey.substring(0, startIndex);
        if (caseStyle.equalsIgnoreCase("lower")) {
            result += rawKey.substring(startIndex, endIndex).toLowerCase();
        } else if (caseStyle.equalsIgnoreCase("upper")) {
            result += rawKey.substring(startIndex, endIndex).toUpperCase();
        }
        result += rawKey.substring(endIndex);

        return result;
    }
}
