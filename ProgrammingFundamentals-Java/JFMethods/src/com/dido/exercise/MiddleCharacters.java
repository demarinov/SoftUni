package com.dido.exercise;

import java.util.Scanner;

public class MiddleCharacters {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        printMiddleChars(str);
    }

    public static void printMiddleChars(String str) {

        int middlePos = str.length()/2;

        if (str.length() % 2 == 0) {
            System.out.printf("%s", str.substring(middlePos-1, middlePos + 1));
        } else {
            System.out.printf("%s", str.substring(middlePos, middlePos + 1));
        }
    }
}
