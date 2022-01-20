package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class AddVat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split(", "))
                .map(s -> Double.parseDouble(s))
                .collect(Collectors.toList());

        UnaryOperator<Double> unary = x -> x * 1.2;

        System.out.println("Prices with VAT:");
        for (Double num : numbers) {

            Double number = unary.apply(num);
            System.out.printf("%.2f%n",number);
        }
    }
}
