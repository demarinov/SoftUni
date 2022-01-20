package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class DecimalToBinaryConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer num = Integer.parseInt(sc.nextLine());

        ArrayDeque<Integer> stackConverter = new ArrayDeque<>();
        while(num > 0) {

            int rem = num % 2;
            num /=2;
            stackConverter.push(rem);
        }

        if (!stackConverter.isEmpty()) {
            stackConverter.stream().forEach(System.out::print);
            System.out.println();
        } else {
            System.out.println(0);
        }
    }
}
