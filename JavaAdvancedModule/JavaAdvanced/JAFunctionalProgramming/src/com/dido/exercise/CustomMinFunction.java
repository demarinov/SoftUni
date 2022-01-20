package com.dido.exercise;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CustomMinFunction {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Function<Integer[],Integer> functionMin = nums -> {
            return Arrays.stream(nums).min(Integer::compareTo).orElse(null);
        };

        Integer minValue = functionMin.apply(numbers.toArray(new Integer[0]));
        System.out.println(minValue);
    }
}
