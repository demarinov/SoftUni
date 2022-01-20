package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());

        List<Double> bombInfo = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());


        detonateBomb(numbers, bombInfo);

        calculateAndPrintSum(numbers);

    }

    public static void calculateAndPrintSum(List<Double> nums) {

        double sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }

        if (sum > Double.valueOf(sum).intValue()) {
            System.out.println(sum);
        } else {
            System.out.printf("%.0f", sum);
        }

    }

    public static void detonateBomb(List<Double> numbers, List<Double> bombInfo) {


        int power = Math.abs(bombInfo.get(1).intValue());
        int i = 0;
        while (numbers.contains(bombInfo.get(0))) {

            i = numbers.indexOf(bombInfo.get(0));
            // remove the main number
            numbers.remove(i);

            // move right and detonate
            int countRemovalsLeft = 0;
            int start = i;
            int end = power;
            // 1 2 2 4 - d2 3
            if (power > numbers.size() - i - 1) {
                end = numbers.size() - i;
            }
            for (int j = 1; j <= end; j++) {
                numbers.remove(i);
            }

            // move left and detonate
            start = i - 1;
            end = power;
            // 1 2 2 4 - d2 3
            if (power > i) {
                end = i;
            }

            for (int j = 1; j <= end; j++) {
                numbers.remove(start);
                start--;
            }

        }
    }
}
