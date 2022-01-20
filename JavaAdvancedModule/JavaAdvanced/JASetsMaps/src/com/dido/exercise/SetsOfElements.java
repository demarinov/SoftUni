package com.dido.exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElements {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] input = sc.nextLine().split("\\s");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        Set<Integer> setOne = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {

            int num = Integer.parseInt(sc.nextLine());
            setOne.add(num);
        }

        Set<Integer> setTwo = new LinkedHashSet<>();
        for (int i = 0; i < m; i++) {

            int num = Integer.parseInt(sc.nextLine());
            setTwo.add(num);
        }

        Set<Integer> intersectionSet = new LinkedHashSet<>();
        for (Integer num : setOne) {

            if (setTwo.contains(num)) {
                intersectionSet.add(num);
            }
        }

        intersectionSet.stream().forEach(s -> System.out.printf("%d ",s));
    }
}
