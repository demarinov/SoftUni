package com.dido.exercise;

import java.util.Scanner;

public class TriplesOfLetters {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int start = 'a';
        int end = 'a' + n;

        for (int i = start; i < end; i++) {

            for (int j = start; j < end; j++) {
                for (int k = start; k < end; k++) {

                    System.out.printf("%c%c%c%n",i,j,k);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            char firstChar = (char) ('a' + i);
//            for (int j = 0; j < n; j++) {
//                char secondChar = (char) ('a' + j);
//                for (int k = 0; k < n; k++) {
//                    char thirdChar = (char) ('a' + k);
//
//                    System.out.printf("%c%c%c%n",firstChar,secondChar,thirdChar);
//                }
//            }
//        }
    }
}
