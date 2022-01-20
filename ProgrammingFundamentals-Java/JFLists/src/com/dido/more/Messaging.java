package com.dido.more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Messaging {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(" "))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        String str = sc.nextLine();
        char[] chars = str.toCharArray();
        int index = 0;
        List<String> result = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            index = getSumOfDigits(numbers.get(i));
            getCharAtIndex(result, index, chars);

        }

//        for (int i = 0; i < chars.length; i++) {
//            System.out.printf("%c ",chars[i]);
//        }
//        System.out.println();
        printResult(result);
    }

    public static void printResult(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.printf("%s",result.get(i));
        }
    }

    public static void getCharAtIndex(List<String> result, int index, char[] chars) {
        int newIndex = index;
        if (newIndex >= chars.length) {
            newIndex = index % chars.length;
        }

        result.add(String.format("%c", chars[newIndex]));
        int len = chars.length;
        for (int i = newIndex+1; i < chars.length; i++) {
            chars[i-1] = chars[i];
            chars[i] = '\0';
        }
        len--;
        char[] oldChars = chars;
        chars = new char[len];
        for (int i = 0; i < len; i++) {
            chars[i] = oldChars[i];
        }
    }

    public static int getSumOfDigits(Integer number) {
        int sum = 0;
        String strNumber  = String.format("%d",number);

        for (int i = 0; i < strNumber.length(); i++) {

            sum += Integer.parseInt(String.format("%c",strNumber.charAt(i)));
        }

        return sum;
    }
}
