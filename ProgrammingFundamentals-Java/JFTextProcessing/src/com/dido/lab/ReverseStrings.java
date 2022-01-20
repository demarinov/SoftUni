package com.dido.lab;

import java.util.Scanner;

public class ReverseStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("end")) {

            String word = input;
            String reversed = "";

            for (int i = word.length()-1; i >=0; i--) {
                reversed += word.charAt(i);
            }

            System.out.printf("%s = %s%n",word, reversed);
            input = sc.nextLine();
        }
    }
}
