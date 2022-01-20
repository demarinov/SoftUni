package com.dido.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GenericSwapInt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Integer> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {

            Integer name = Integer.parseInt(sc.nextLine());
            elements.add(name);
        }

        int[] indexes = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        swap(elements, indexes[0], indexes[1]);

        elements.stream().forEach(s -> {

            System.out.printf("%s: %d%n", s.getClass(),s);
        });
    }

    public static <T> void swap(List<T> elements, int idxOne, int idxTwo) {

        if (idxOne < 0 || idxOne >= elements.size()
                || idxTwo < 0 || idxTwo>=elements.size()) {
            return;
        }

        T element = elements.get(idxOne);
        elements.set(idxOne, elements.get(idxTwo));
        elements.set(idxTwo, element);


    }
}
