package com.dido.exercise;

import java.util.Scanner;

public class LettersChangeNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] data = sc.nextLine().split("\\s+");

        double sum = 0.0d;
        for (int i = 0; i < data.length; i++) {

            String code = data[i];

            double num = decode(code);
            sum +=num;
        }

        System.out.printf("%.2f",sum);
    }

    public static double decode(String code) {

        Character firstChar = code.charAt(0);
        Character lastChar = code.charAt(code.length()-1);
        Integer number = Integer.parseInt(code.substring(1,code.length()-1));

        double result = 0.0d;

        int pos = -1;

        // 97-122
        int aplhaBase = 96;
        // 65-90
        int aplhaBaseCapital = 64;

        if (Character.isUpperCase(firstChar)) {
            pos = firstChar - aplhaBaseCapital;
            result = 1.0 * number / pos;
        } else if (Character.isLowerCase(firstChar)) {
            pos = firstChar - aplhaBase;
            result = 1.0 * number * pos;
        }

        if (Character.isUpperCase(lastChar)) {
            pos = lastChar - aplhaBaseCapital;
            result = result - pos;
        } else if (Character.isLowerCase(lastChar)) {
            pos = lastChar - aplhaBase;
            result = result + pos;
        }

        return result;
    }
}
