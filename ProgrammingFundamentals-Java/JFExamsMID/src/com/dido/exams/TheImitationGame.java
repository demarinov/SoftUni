package com.dido.exams;

import java.util.Scanner;

public class TheImitationGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String message = sc.nextLine();

//        •	Move {number of letters}
//        o	Moves the first n letters to the back of the string.
//•	Insert {index} {value}
//        o	Inserts the given value before the given index in the string.
//•	ChangeAll {substring} {replacement}
//        o	Changes all occurrences of the given substring with the replacement text.

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("decode")) {

            String[] commandData = command.split("\\|");

            String instruction = commandData[0];
            if (instruction.equalsIgnoreCase("move")) {
               // o	Moves the first n letters to the back of the string.
               int n = Integer.parseInt(commandData[1]);

               message = moveLettersBack(message, n);

            } else if (instruction.equalsIgnoreCase("insert")) {
                //o	Inserts the given value before the given index in the string.
                int index = Integer.parseInt(commandData[1]);
                String value = commandData[2];

                message = insertBeforeIndex(message, index, value);
            } else if (instruction.equalsIgnoreCase("changeall")) {
                //•	ChangeAll {substring} {replacement}
                String subStr = commandData[1];
                String replacement = commandData[2];

                message = message.replaceAll(subStr,replacement);
            }

            command = sc.nextLine();
        }

        System.out.println("The decrypted message is: "+message);
    }

    public static String insertBeforeIndex(String message, int index, String value) {

        String result = "";

        result += message.substring(0,index);
        result += value;
        result += message.substring(index);

        return result;
    }

    public static String moveLettersBack(String message, int n) {

        String result = "";

        result += message.substring(n);
        result += message.substring(0,n);

        return result;
    }
}
