package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Function<String, Integer> funcParseInt = s -> Integer.parseInt(s);
        List<Integer> numbers = Arrays.stream(sc.nextLine().split(", "))
                .map(funcParseInt)
                .collect(Collectors.toList());

        Integer sum = numbers.stream().mapToInt(d -> d).sum();
        Integer count = numbers.size();

        System.out.printf("Count = %d%n",count);
        System.out.printf("Sum = %d",sum);
    }
}
