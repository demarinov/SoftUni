package com.dido.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseExclude {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Integer n = Integer.parseInt(sc.nextLine());

        Predicate<Integer> predicateDiv = s -> s % n != 0;

        Collections.reverse(nums);
        List<Integer> filteredNums = nums.stream().filter(predicateDiv).collect(Collectors.toList());

        Consumer<Integer> consumerPrint = s -> System.out.printf("%d ",s);
        filteredNums.forEach(consumerPrint);
        System.out.println();
    }
}
