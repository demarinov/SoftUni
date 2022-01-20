package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindSmallestElement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // index (if more than one such elements exist, print the index of the rightmost one).

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Function<List<Integer>, Integer> functionMin = s ->{
            Integer minIdx = -1;

            Integer min = s.stream().min(Integer::compareTo).orElse(null);

            for (int i = 0; i < s.size(); i++) {

                if (!min.equals(s.get(i))) {
                    continue;
                }

                minIdx = i;
            }

            return minIdx;
        };

        System.out.println(functionMin.apply(nums));
    }
}
