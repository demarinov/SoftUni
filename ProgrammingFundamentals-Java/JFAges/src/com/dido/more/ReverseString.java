package com.dido.more;

import java.util.Scanner;

public class ReverseString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        byte[] bytes = str.getBytes();
        byte[] revBytes = new byte[bytes.length];

        for (int i = 0; i <= bytes.length - 1; i++) {
            revBytes[i] = bytes[bytes.length - 1 - i];
        }

        System.out.print(new String(revBytes));
    }
}
