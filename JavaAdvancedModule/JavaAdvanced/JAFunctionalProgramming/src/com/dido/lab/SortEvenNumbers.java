package com.dido.lab;

import java.util.*;
import java.util.stream.Collectors;

public class SortEvenNumbers {

    public static void main(String[] args) {
        // Write a program that reads one line of Integers separated by ", ".
        //•	Print the even numbers
        //•	Sort them in ascending order
        //•	Print them again.
        //Use 2 Lambda Expresions to do so.

        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split(", "))
        .map(d -> Integer.parseInt(d))
//                .filter(n -> n % 2 != 0)
                .collect(Collectors.toList());

        numbers.removeIf(n -> n % 2 != 0);
        String result = numbers.stream().map(Objects::toString)
                .collect(Collectors.joining(", "));
        System.out.println(result);

//        numbers.sort(Comparator.naturalOrder());

        numbers.sort(Integer::compareTo);
        result = numbers.stream().map(Objects::toString)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
