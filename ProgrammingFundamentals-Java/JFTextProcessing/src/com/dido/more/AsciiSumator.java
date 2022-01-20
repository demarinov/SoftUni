package com.dido.more;

import java.util.Scanner;

public class AsciiSumator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Character startChar = sc.nextLine().charAt(0);
        Character endChar = sc.nextLine().charAt(0);

        String text = sc.nextLine();

        // find the sum of text(ascii codes) between chars

        int sumAscii = 0;
        for (int i = 0; i < text.length(); i++) {

            Character c = text.charAt(i);

            if (c > startChar && c < endChar) {
                sumAscii += c;
            }

        }

        System.out.println(sumAscii);

    }
}
