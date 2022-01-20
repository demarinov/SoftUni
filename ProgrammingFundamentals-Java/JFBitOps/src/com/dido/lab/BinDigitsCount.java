package com.dido.lab;

import java.util.Scanner;

public class BinDigitsCount {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String n = sc.nextLine();
        String b = sc.nextLine();

        int num = Integer.parseInt(n);

        String binStr = convertToBinString(num);
        System.out.println(binStr);

//        int count = countDigit(binStr, b);
        int count = countDigit(Integer.parseInt(n), Integer.parseInt(b));

        System.out.println(count);
    }

    public static int countDigit(String binStr, String b) {

        int counter = 0;
        // 1st method
        for (int i = 0; i < binStr.length(); i++) {

            if (binStr.charAt(i) == b.charAt(0)) {
                counter++;
            }
        }

        return counter;
    }

    public static int countDigit(int num, int b) {
        int counter = 0;
        // 2nd method using bit masks

        int countShifts = 0;
        int shiftedNum = Integer.MAX_VALUE;
        while(shiftedNum > 1) {

            // 1010 -> 3     0101, 0010, 0001, 0000
            shiftedNum = num >> countShifts;
            if ((shiftedNum & 1) == b) {

                counter++;
            }

            countShifts++;
        }

        return counter;
    }

    public static String convertToBinString(int num) {

        int div = num;
        String result = "";

        while(div > 0) {

            int rem = div % 2;
            result += String.format("%d",rem);
            div /= 2;

        }

        return reverseStr(result);
    }

    public static String reverseStr(String str) {

        String result = "";
        for (int i = str.length()-1; i >= 0; i--) {

            result += String.format("%c",str.charAt(i));
        }

        return  result;
    }
}
