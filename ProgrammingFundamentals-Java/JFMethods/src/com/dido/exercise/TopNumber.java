package com.dido.exercise;

import java.util.Scanner;

public class TopNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

//        •	Its sum of digits is divisible by 8, e.g. 8, 16, 88.
//        •	Holds at least one odd digit, e.g. 232, 707, 87578.

        int[] numbers = getSumDivisibleByEight(n);
        checkOddDigits(numbers);

        printTopNumbers(numbers);

    }

    public static void printTopNumbers(int[] topNumbers) {

        for (int i = 0; i < topNumbers.length; i++) {

            if (topNumbers[i] != 0) {
                System.out.println(topNumbers[i]);
            }
        }
    }
    
    public static int[] getSumDivisibleByEight(int n) {
        
        int[] result = new int[n];
        int j = 0;

        for (int i = 1; i <= n; i++) {

            int sumOfDigits = getSumOfDigits(i);

            if (sumOfDigits % 8 == 0) {
                result[j] = i;
                j++;
            }
        }

        return result;
    }

    public static int getSumOfDigits(int num) {

        String[] numStrArray = String.format("%d",num).split("");
        int sumOfDigits = 0;

        for (int i = 0; i < numStrArray.length; i++) {

            sumOfDigits += Integer.parseInt(numStrArray[i]);
        }

        return sumOfDigits;
    }
    
    public static void checkOddDigits(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            String num = String.format("%d",nums[i]);

            if (hasEvenDigitsOnly(num)) {
                nums[i] = 0;
            }

        }

    }

    public static boolean hasEvenDigitsOnly(String num) {

        for (int i = 0; i < num.length(); i++) {

            int n = Integer.parseInt(String.format("%c",num.charAt(i)));

            if (n % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}
