package com.dido.exercise;

import java.util.Scanner;

public class ReplaceRepeatingChars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder builder = new StringBuilder(sc.nextLine());

        printReplacedStringV1(builder);
//        printReplacedStringV2(builder);
    }

    public static  void printReplacedStringV1(StringBuilder builder) {

        for (int i = 0; i < builder.length()-1; i++) {

            Character c1 = builder.charAt(i);
            Character c2 = builder.charAt(i+1);

            if (c1.equals(c2)) {
                builder.replace(i, i+2, String.format("%c",c1));
                i--;
            }
        }

        System.out.println(builder.toString());
    }

    public static void printReplacedStringV2(StringBuilder builder) {

        int startIdx = 0;
        int count = 1;
        Character searchChar = builder.charAt(0);
        for (int i = 1; i < builder.length(); i++) {

            Character c = builder.charAt(i);

            if (searchChar.equals(c)) {
                count++;
            } else {
                int endIdx = startIdx + count;
                builder.replace(startIdx, endIdx, String.format("%c",searchChar));
                i-=count-1;
                startIdx = i;
                count = 1;
                searchChar = c;
            }
        }

        // for the last repeating chars
        builder.replace(startIdx, startIdx+count, String.format("%c",searchChar));

        System.out.println(builder.toString());
    }
}
