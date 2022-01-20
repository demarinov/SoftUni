package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MaximumElement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Integer n = Integer.parseInt(sc.nextLine());

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            String[] input = sc.nextLine().split("\\s+");
            String type = input[0];

            switch(type) {
                case "1":
                    Integer num = Integer.parseInt(input[1]);
                    stack.push(num);
                    break;
                case "2":
                    stack.pop();
                    break;
                case "3":
                    if (!stack.isEmpty()) {
                        int max = stack.stream().mapToInt(d -> d).max().getAsInt();
                        System.out.println(max);
                    }
                    break;

                default:
                    break;
            }


        }


    }
}
