package com.dido.exams2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Numbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        Double averageValue = numbers.stream().mapToInt(d -> d).average().getAsDouble();

        List<Integer> topFive = numbers.stream().filter(n -> n.intValue() > averageValue.doubleValue())
                .collect(Collectors.toList());

        if (topFive.isEmpty()) {
            System.out.println("No");
        } else {
            topFive.stream()
                    .sorted((d1,d2) -> d2.compareTo(d1))
                    .limit(5)
                    .map(s -> String.format("%d ",s))
                    .forEach(System.out::print);
        }
    }
}
