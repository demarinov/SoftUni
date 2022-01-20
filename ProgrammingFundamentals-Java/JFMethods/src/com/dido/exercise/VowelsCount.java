package com.dido.exercise;

import java.util.Scanner;

public class VowelsCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        System.out.println(countVowels(str));
    }

    public static int countVowels(String str) {

        int countVowels = 0;

        for (int i = 0; i < str.length(); i++) {

            switch (str.toLowerCase().charAt(i)) {

                case 'i':
                case 'a':
                case 'o':
                case 'e':
                case 'u':
                    countVowels++;
                    break;
            }
        }

        return countVowels;
    }
}
