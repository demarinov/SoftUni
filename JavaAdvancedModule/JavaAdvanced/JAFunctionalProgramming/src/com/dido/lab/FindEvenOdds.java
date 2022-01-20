package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class FindEvenOdds {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] ranges = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        String oddOrEven = sc.nextLine();
        BiPredicate<Integer,String> oddPredicate = (i,s) -> "odd".equals(s) && i % 2 != 0;
        BiPredicate<Integer,String> evenPredicate = (i,s) -> "even".equals(s) && i % 2 == 0;


        for (int i = ranges[0]; i <= ranges[1]; i++) {

            if (oddPredicate.test(i, oddOrEven)) {
                System.out.printf("%d ",i);
            } else if (evenPredicate.test(i, oddOrEven)) {
                System.out.printf("%d ",i);
            }

        }
    }
}
