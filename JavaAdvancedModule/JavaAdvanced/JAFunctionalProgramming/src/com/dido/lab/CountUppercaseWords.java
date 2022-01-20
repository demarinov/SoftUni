package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountUppercaseWords {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> words = Arrays.stream(sc.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Predicate<String> predicate = s -> Character.isUpperCase(s.charAt(0));
        List<String> capitalLetterWords = words.stream().filter(predicate).collect(Collectors.toList());
        Integer count = capitalLetterWords.size();
        System.out.println(count);
        capitalLetterWords.forEach(System.out::println);
    }
}
