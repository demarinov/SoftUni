package com.dido.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ListOfPredicates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        int[] nums = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        Predicate<Integer> predicateDiv = s -> {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (s % nums[i] == 0) {
                    count++;
                }
            }

            if (count == nums.length) {
                return true;
            }

            return false;
        };

        List<Integer> resultNums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            if (predicateDiv.test(i)) {
                resultNums.add(i);
            }
        }

        Consumer<Integer> consumerPrint = s -> System.out.printf("%d ",s);
        resultNums.stream().forEach(consumerPrint);
    }
}
