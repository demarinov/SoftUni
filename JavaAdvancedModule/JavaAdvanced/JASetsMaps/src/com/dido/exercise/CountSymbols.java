package com.dido.exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String symbols =  sc.nextLine();

        Map<Character, Integer> charOccurrencesMap = new TreeMap<>();
        for (int i = 0; i < symbols.length(); i++) {

            Character c = symbols.charAt(i);

            charOccurrencesMap.putIfAbsent(c, 0);
            charOccurrencesMap.put(c, charOccurrencesMap.get(c)+1);
        }

        charOccurrencesMap.entrySet().stream()
                .forEach(e -> System.out.printf("%c: %d time/s%n",e.getKey(), e.getValue()));
    }
}
