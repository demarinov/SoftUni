package com.dido.exercise;

import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomComparator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        IntPredicate predicateEven = s -> s % 2 == 0;
        IntPredicate predicateOdd = s -> s % 2 != 0;
        Arrays.sort(nums);

        List<Integer> result =
                Arrays.stream(nums).filter(predicateEven).mapToObj(s-> s).collect(Collectors.toList());

        result.addAll(Arrays.stream(nums).filter(predicateOdd).mapToObj(s-> s).collect(Collectors.toList()));
        Consumer<Integer> consumerPrint = s -> System.out.printf("%d ",s);
        result.forEach(consumerPrint);
    }
}
