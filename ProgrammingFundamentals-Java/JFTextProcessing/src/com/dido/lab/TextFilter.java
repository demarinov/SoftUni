package com.dido.lab;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TextFilter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] bannedWords = sc.nextLine().split(", ");
        String text = sc.nextLine();

        for (int i = 0; i < bannedWords.length; i++) {

            String bannedWord = bannedWords[i];

            int idx = text.indexOf(bannedWord);

            while(idx >= 0) {

                // or write method that repeat the replacement...
                String repeatedString = "";

                for (int j = 0; j < bannedWord.length(); j++) {
                    repeatedString +="*";
                }
                text = text.replace(bannedWord, repeatedString);

                idx = text.indexOf(bannedWord);
            }
        }

        System.out.println(text);


    }
}
