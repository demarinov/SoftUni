package com.dido.exercise;

import java.util.Scanner;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        System.out.println(encryptText(input));
    }

    public static String encryptText(String input) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            Character c = input.charAt(i);
            builder.append(String.format("%c",c+3));
        }

        return builder.toString();
    }
}
