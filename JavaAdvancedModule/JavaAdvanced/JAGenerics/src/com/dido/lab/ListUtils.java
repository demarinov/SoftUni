package com.dido.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListUtils {

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        Collections.addAll(integers,1,2,18,2,-1);

        Integer maxInteger = ListUtils.getMax(integers);
        System.out.println(maxInteger);

        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, "a","b","c");

        String minString = ListUtils.getMin(strings);
        System.out.println(minString);
    }


    public static <T extends Comparable<T>> T getMax(List<T> list) {

        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty list!");
        }

        return list.stream().sorted(Comparator.reverseOrder()).limit(1).findFirst().orElse(null);
    }

    public static <T extends Comparable<T>> T getMin(List<T> list) {


        if (list.isEmpty()) {
            throw new IllegalArgumentException("Empty list!");
        }

        return list.stream().sorted().limit(1).findFirst().orElse(null);
    }
}
