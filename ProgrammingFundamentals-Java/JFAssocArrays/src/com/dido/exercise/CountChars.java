package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class CountChars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> inputList = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Map<Character, Integer> countMap = new LinkedHashMap<>();

        for (int i = 0; i < inputList.size(); i++) {

            countCharacters(countMap, inputList.get(i));
        }

        countMap.entrySet().stream().map(e -> String.format("%c -> %d",e.getKey(), e.getValue()))
                .forEach(System.out::println);

    }

    public static void countCharacters(Map<Character, Integer> countMap, String text) {

        for (int i = 0; i < text.length(); i++) {

            char c = text.charAt(i);

            countMap.putIfAbsent(c,0);
            countMap.put(c,countMap.get(c)+1);
        }
    }
}
