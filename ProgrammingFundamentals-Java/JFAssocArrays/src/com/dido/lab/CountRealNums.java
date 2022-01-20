package com.dido.lab;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class CountRealNums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());

        Map<Double, Integer> numMap = new TreeMap<>();

        for (int i = 0; i < numbers.size(); i++) {

            if (!numMap.containsKey(numbers.get(i))) {
                numMap.put(numbers.get(i),0);
            }

            numMap.put(numbers.get(i),numMap.get(numbers.get(i))+1);
        }

        for (Map.Entry<Double, Integer> entry:numMap.entrySet()) {

            DecimalFormat df = new DecimalFormat("#.######");
            System.out.printf("%s -> %d%n", df.format(entry.getKey()), entry.getValue());
        }
    }
}
