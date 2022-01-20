package com.dido.exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PeriodicTable {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        You are given an n number of chemical compounds. You need to keep track
//        of all chemical elements
//        used in the compounds and at the end print all unique ones in ascending order:

        int count = Integer.parseInt(sc.nextLine());

        Set<String> elementSet = new TreeSet<>();
        for (int i = 0; i < count; i++) {

            String[] elements = sc.nextLine().split("\\s");

            for (int j = 0; j < elements.length; j++) {

                elementSet.add(elements[j]);
            }
        }

        elementSet.stream().forEach(s -> System.out.printf("%s ",s));
    }
}
