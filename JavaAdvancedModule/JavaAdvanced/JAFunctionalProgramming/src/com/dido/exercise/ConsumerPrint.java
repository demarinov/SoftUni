package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class ConsumerPrint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] array = sc.nextLine().split("\\s+");

        Consumer<String> consumer = s -> System.out.println(s);
        Arrays.asList(array).forEach(consumer);
    }
}
