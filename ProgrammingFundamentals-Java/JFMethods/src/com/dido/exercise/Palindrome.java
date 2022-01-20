package com.dido.exercise;

import java.util.Scanner;

public class Palindrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("End")) {

            String num = input;
            System.out.println(isPalindrome(num));

            input = sc.nextLine();
        }
    }

    public static String isPalindrome(String num) {

        String revNum = "";
        for (int i = num.length()-1; i >= 0; i--) {
            revNum += String.format("%c",num.charAt(i));
        }


        if (revNum.equals(num)) {
            return "true";
        }

        return "false";
    }
}
