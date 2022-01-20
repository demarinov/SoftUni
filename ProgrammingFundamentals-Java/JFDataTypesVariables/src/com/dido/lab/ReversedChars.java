package com.dido.lab;

import java.util.Scanner;

public class ReversedChars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char c1 = '\0';
        char c2 = '\0';
        char c3 = '\0';

        for (int i = 1; i <= 3; i++) {

            if (i == 1) {
                c1 = sc.nextLine().charAt(0);
            } else if (i == 2) {
                c2 = sc.nextLine().charAt(0);
            } else if (i == 3) {
                c3 = sc.nextLine().charAt(0);
            }
        }

        System.out.printf("%c %c %c",c3,c2,c1);
    }
}
