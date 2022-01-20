package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Largest3Nums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).sorted((i1,i2) ->i2.compareTo(i1) ).limit(3)
                .collect(Collectors.toList());

        nums.stream().map(s -> String.format("%d ",s))
                .forEach(System.out::print);
    }
}
