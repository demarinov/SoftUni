package com.dido.exams;

import java.util.*;
import java.util.stream.Collectors;

public class Numbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        double avValue = 0.0d;
        double sum = 0.0d;
        List<Integer> topFiveAboveAvg = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            sum += numbers.get(i);
        }

        avValue = 1.0 * sum / numbers.size();

        for (int i = 0; i < numbers.size(); i++) {

            if (numbers.get(i) > avValue) {

                topFiveAboveAvg.add(numbers.get(i));

            }
        }

        Collections.sort(topFiveAboveAvg);
        Collections.reverse(topFiveAboveAvg);

        if(topFiveAboveAvg.isEmpty()) {
            System.out.println("No");
        } else if (topFiveAboveAvg.size() >= 5) {
            for (int i = 0; i < 5; i++) {

                System.out.printf("%d ",topFiveAboveAvg.get(i));
            }
        } else {
            System.out.println(topFiveAboveAvg.toString().replaceAll("[\\[\\],]",""));
        }
    }
}
