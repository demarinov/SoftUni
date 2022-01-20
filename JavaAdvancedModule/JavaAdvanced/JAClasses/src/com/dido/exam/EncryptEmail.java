package com.dido.exam;


import java.util.Scanner;

// Problem 1
public class EncryptEmail {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        StringBuilder outputEmail = new StringBuilder(input);


        // do all until Complete

        input = sc.nextLine();

        while (!"Complete".equals(input)) {

            String[] commandData = input.split("\\s");

            switch (commandData[0]) {

                case "Make":
                    // Make Upper - replace all letters
                    // Make Lower - replace all letters
                    if ("Lower".equals(commandData[1])) {
                        makeLower(outputEmail);
                    } else if ("Upper".equals(commandData[1])) {
                        makeUpper(outputEmail);
                    }

                    System.out.println(outputEmail.toString());
                    break;
                case "GetDomain":
                    // GetDomain {count} - print last count chars
                    Integer count = Integer.parseInt(commandData[1]);
                    getDomain(outputEmail, count);

                    break;
                case "GetUsername":
                    // GetUsername -print substring from start until @ sym
                    // if email has no @ print
                    // "The email {email} doesn't contain the @ symbol.
                    getUsername(outputEmail);
                    break;
                case "Replace":
                    // Replace {char} - replace all occurences of char
                    // with a dash - and print
                    String character = commandData[1];
                    replaceChar(outputEmail, character);
                    System.out.println(outputEmail.toString());
                    break;
                case "Encrypt":
                    // Encrypt - get the ascii value of each symbol
                    // and print the result with space
                    encryptEmail(outputEmail);
                    break;
                default:
                    break;


            }

            input = sc.nextLine();
        }
    }

    public static void encryptEmail(StringBuilder outputEmail) {

        String input = outputEmail.toString();
        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            System.out.printf("%d ",(int)c);
        }
        System.out.println();
    }

    public static void getUsername(StringBuilder outputEmail) {

//        The email {email} doesn't contain the @ symbol.
        String input = outputEmail.toString();
        if (!input.contains("@")) {
            System.out.printf("The email %s doesn't contain the @ symbol.%n",input);
        } else {
            int index = input.indexOf('@');
            String output = input.substring(0, index);

            System.out.println(output);
        }
    }

    public static void replaceChar(StringBuilder outputEmail, String c) {

        String input = outputEmail.toString().replace(c,"-");
        outputEmail.delete(0, outputEmail.length());
        outputEmail.append(input);
    }

    public static void getDomain(StringBuilder outputEmail, int count) {

        String input = outputEmail.toString();
        int startIdx = outputEmail.length() - count;
        StringBuilder output = new StringBuilder();

        if (startIdx < 0
                || startIdx >= input.length() || count < 0) {

            return;
        }

        for (int i = startIdx; i < startIdx + count; i++) {
            output.append(String.format("%c",input.charAt(i)));
        }

        System.out.println(output);
    }

    public static void makeUpper(StringBuilder outputEmail) {

        String input = outputEmail.toString().toUpperCase();
        outputEmail.delete(0, outputEmail.length());
        outputEmail.append(input);
    }

    public static void makeLower(StringBuilder outputEmail) {

        String input = outputEmail.toString().toLowerCase();
        outputEmail.delete(0, outputEmail.length());
        outputEmail.append(input);
    }
}
