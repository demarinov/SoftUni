package com.dido.lab;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class FilterByAge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Write a program that reads an integer N on the first line.
        // And on next N lines read pairs of "[name], [age]". Then read three more lines with:
        //•	Condition - "younger" or "older"
        //•	Age - Integer
        //•	Format - "name", "age" or "name age"
        //Depending on the condition, print the pairs in the right format.
        //Don’t use any build-in functionality. Write your own methods.

        Integer n = Integer.parseInt(sc.nextLine());

        Map<String, Integer> nameAge = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] nameAgePair = sc.nextLine().split(",\\s");
            nameAge.putIfAbsent(nameAgePair[0], Integer.parseInt(nameAgePair[1]));
        }

        String conditionString = sc.nextLine();
        Integer ageLimit = Integer.parseInt(sc.nextLine());
        String[] format = sc.nextLine().split("\\s");

        BiPredicate<String, Integer> conditionPredicate = (cs,a) -> {

            if ("older".equals(cs)) {
                return a >= ageLimit;
            } else if ("younger".equals(cs)) {
                return a <= ageLimit;
            }

            return false;
        };

        Predicate<Integer> formatLenPredicate = d -> d > 1 ? true : false;
        Predicate<String> namePredicate = na -> "name".equals(na) ? true : false;
        Predicate<String> agePredicate = a -> "age".equals(a) ? true : false;

        for(String name : nameAge.keySet()) {

            Integer ageValue = nameAge.get(name);

            if (conditionPredicate.test(conditionString, ageValue)) {

                if (formatLenPredicate.test(format.length)) {
                    System.out.printf("%s - %d%n",name, ageValue);
                } else if (namePredicate.test(format[0])) {
                    System.out.printf("%s%n", name);
                } else if (agePredicate.test(format[0])) {
                    System.out.printf("%d%n", ageValue);
                }

            }

        }
    }
}
