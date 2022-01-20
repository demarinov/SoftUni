package com.dido.exercise;

import java.util.Scanner;

public class CharsInRange {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char c1 = sc.nextLine().charAt(0);
        char c2 = sc.nextLine().charAt(0);

        printCharsInBetween(c1,c2);
    }

    public static void printCharsInBetween(char c1, char c2) {
        char min = c1;
        char max = c2;

        if (c1 > c2) {
            min = c2;
            max = c1;
        }

        for (int i = min+1; i < max; i++) {

            System.out.printf("%c ",i);
        }
    }
}
