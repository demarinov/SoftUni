package com.dido.lab;

import java.util.Scanner;

public class CharsToString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String result = "";

        for (int i = 1; i <= 3; i++) {

            char c = sc.nextLine().charAt(0);

            result += String.format("%c",c);
        }

        System.out.println(result);
    }
}
