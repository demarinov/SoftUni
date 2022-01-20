package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WordFilter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .filter(s -> s.length() % 2 == 0).collect(Collectors.toList());

        words.forEach(System.out::println);
    }
}
