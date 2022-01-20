package com.dido.more;

import java.util.Scanner;

public class DataTypeFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equals("END")) {

            boolean isNum = true;
            String type = "";
            // check for number type or not
            for (int i = 0; i <= input.length() - 1; i++) {

                switch(input.charAt(i)) {

                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    case '.':
                    case '-':
                        break;
                    default:
                        isNum = false;
                        break;
                }
            }

            if (isNum) {

                if (input.contains(".")) {
                    // float
                    type = "floating point";
                } else {
                    // int
                    type = "integer";
                }

            } else {
                if (input.length() == 1) {
                    // char
                    type = "character";
                } else if (input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false")) {
                    // boolean
                    type = "boolean";
                } else {
                    // string
                    type = "string";
                }
            }

            System.out.printf("%s is %s type%n",input, type);
            input = sc.nextLine();
        }
    }
}
