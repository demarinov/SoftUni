package com.dido.exercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String regex = ">>(\\w+)<<([0-9]+.?[0-9]+)!([0-9]+)";

        // Write a program to calculate the total cost of different types of furniture.
        // allowing furnitures with the same name even though different type ???
        String input = sc.nextLine();

        double sum = 0.0d;
        List<String> names = new ArrayList<>();

        while (!input.equalsIgnoreCase("purchase")) {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {

                double price = Double.parseDouble(matcher.group(2));
                double quantity = Double.parseDouble(matcher.group(3));

                double amount = price * quantity;

                String name = matcher.group(1);
                sum+=amount;
                names.add(name);

            }

            input = sc.nextLine();

        }

        System.out.println("Bought furniture:");
         names.stream().forEach(System.out::println);
        System.out.printf("Total money spend: %.2f%n", sum);


    }
}
