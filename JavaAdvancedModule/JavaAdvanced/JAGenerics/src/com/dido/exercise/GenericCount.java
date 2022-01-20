package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenericCount {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        List<String> elements = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String element  = sc.nextLine();

            elements.add(element);
        }

        String element = sc.nextLine();
        System.out.println(count(elements,element));
    }

    public static <T extends Comparable<T>> int count(List<T> elements, T element) {

        int count = 0;

        for(T elem : elements) {
            if (elem.compareTo(element) > 0) {
                count++;
            }
        }

        return count;

    }
}
