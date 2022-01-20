package com.dido.lab;

import java.util.*;
import java.util.stream.Collectors;

public class OddOccurrences {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> elements = Arrays.stream(sc.nextLine().split("\\s+")).map(s -> s.toLowerCase())
                .collect(Collectors.toList());

        Map<String, Integer> elementMap = new LinkedHashMap<>();

        for (int i = 0; i < elements.size(); i++) {

            String element = elements.get(i);
            elementMap.putIfAbsent(element, 0);
            elementMap.put(element, elementMap.get(element)+1);
        }

        List<String> finalElements = elementMap.entrySet().stream()
                .filter(e -> e.getValue() % 2 != 0)
                .map(e -> e.getKey())
                .collect(Collectors.toList());

        for (int i = 0; i < finalElements.size(); i++) {

            System.out.printf("%s", finalElements.get(i));
            if (i != finalElements.size()-1) {
                System.out.printf(", ");
            }
        }
    }
}
