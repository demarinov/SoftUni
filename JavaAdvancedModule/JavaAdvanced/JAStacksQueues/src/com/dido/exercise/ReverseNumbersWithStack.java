package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class ReverseNumbersWithStack {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();
        Deque<Integer> stackNums = new ArrayDeque<>();

        for (int i = 0; i < numbers.length; i++) {

            stackNums.push(numbers[i]);
        }

        stackNums.stream()
                .map(d -> String.format("%d ",d))
                .forEach(System.out::print);


    }
}
