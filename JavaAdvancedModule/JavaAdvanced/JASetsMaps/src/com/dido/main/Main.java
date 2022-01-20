package com.dido.main;

import java.util.*;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {
	// write your code here

        String flower = "Kokiche";

        Function<String, Integer> funcLen = s -> s.length();

        System.out.println("Function in Java ->"+funcLen.apply(flower));

        Consumer<String> consumeLen = s -> System.out.println("Consumer in Java ->"+s.length());

        consumeLen.accept(flower);

        Supplier<String> supp = () -> flower;

        System.out.println("Supplier in Java->"+supp.get());

        Predicate<String> predi = s -> s.length()>10;

        System.out.println("Predicate in Java->"+predi.test(flower));

        BiFunction<String, Integer, String> biFunc = (s,l) -> s+l;
        System.out.println("BiFunction in Java->"+biFunc.apply(flower,flower.length()));

        BiPredicate<String, Integer> biPredi = (s,l) -> s.length() > l;

        System.out.println("BiPredicate in Java->"+biPredi.test(flower,6));

        List<Integer> numbers =new LinkedList<>();
        numbers.add(2);
        numbers.add(1);
        numbers.add(5);
        numbers.add(7);

        Collections.sort(numbers, Comparator.reverseOrder());

        numbers.stream().forEach(System.out::println);

    }
}
