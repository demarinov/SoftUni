package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateForNames {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<String> names = Arrays.stream(sc.nextLine().split("\\s"))
                .collect(Collectors.toList());

        Predicate<String> predicateLess = s -> s.length() <= n;

        Consumer<String> consumerPrint = s -> System.out.println(s);

        names.stream().filter(predicateLess).forEach(consumerPrint);
    }
}
