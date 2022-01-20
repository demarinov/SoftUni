package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class RepeatStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] words = sc.nextLine().split("\\s+");

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < words.length; i++) {

            int count = words[i].length();

            for (int j = 0; j < count; j++) {

                builder.append(words[i]);
            }
        }

        System.out.println(builder.toString());
    }
}
