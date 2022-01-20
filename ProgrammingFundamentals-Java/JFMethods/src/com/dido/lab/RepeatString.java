package com.dido.lab;

import java.util.Scanner;

public class RepeatString {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        int n = Integer.parseInt(sc.nextLine());

        System.out.println(repeatString(str,n));
    }

    public static String repeatString(String str,int n) {

        String result = str;
        for (int i = 1; i <= n - 1; i++) {
            result += str;
        }

        return result;
    }
}
