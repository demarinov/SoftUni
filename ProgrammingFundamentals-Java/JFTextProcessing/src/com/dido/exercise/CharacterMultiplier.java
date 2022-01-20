package com.dido.exercise;

import java.util.Scanner;

public class CharacterMultiplier {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] data = sc.nextLine().split(" ");

        if (data.length >= 2) {
            Integer result = multiplyCharacters(data[0], data[1]);
            System.out.println(result);
        }
    }

    public static Integer multiplyCharacters(String str1, String str2) {

        int minLength = 0;
        String remChars = "";

        if (str1.length() == 0 && str2.length() == 0) {
            return 0;
        }

        if (str1.length() > str2.length()) {
            minLength = str2.length();
            remChars += str1.substring(minLength);
        } else {
            minLength = str1.length();
            remChars += str2.substring(minLength);
        }

        Integer sum = 0;
        for (int i = 0; i < minLength; i++) {

            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);

            sum += (c1 * c2);
        }

        for (int i = 0; i < remChars.length(); i++) {

            char c = remChars.charAt(i);
            sum += c;

        }

        return sum;
    }
}
