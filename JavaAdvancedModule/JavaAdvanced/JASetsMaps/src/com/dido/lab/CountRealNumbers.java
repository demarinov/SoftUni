package com.dido.lab;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CountRealNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program that counts the occurrence of real numbers. The input is a single
//        line with real numbers separated by spaces. Print the numbers in the order of appearance.
//        All numbers must be formatted to one digit after the decimal point.

        double[] realNums = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToDouble(n -> Double.parseDouble(n))
                .toArray();

        Map<Double, Integer> occurrences = new LinkedHashMap<>();
        for (double num : realNums) {
            occurrences.putIfAbsent(num, 0);
            occurrences.put(num, occurrences.get(num)+1);
        }

        occurrences.entrySet().stream().map(e -> String.format("%.1f -> %d",e.getKey(), e.getValue()))
                .forEach(System.out::println);
    }
}
