package com.dido.lab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MergingLists {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> firstList = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        List<Integer> secondList = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < Math.min(firstList.size(),secondList.size()); i++) {

            resultList.add(firstList.get(i));
            resultList.add(secondList.get(i));

            firstList.remove(i);
            secondList.remove(i);
            i--;
        }

        if (firstList.isEmpty()) {
            resultList.addAll(secondList);
        } else {
            resultList.addAll(firstList);
        }

        System.out.println(resultList.toString().replaceAll("[\\[\\],]",""));

    }
}
