package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RandomizeWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        for (int i = 0; i < words.size(); i++) {

            int posNew = new Random().nextInt(words.size());
            String temp = words.get(posNew);
            words.set(posNew, words.get(i));
            words.set(i, temp);
        }

        words.stream().forEach(System.out::println);

    }
}
