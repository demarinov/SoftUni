package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BasicStackOps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] nums = sc.nextLine().split("\\s+");

        int n = Integer.parseInt(nums[0]);
        int p = Integer.parseInt(nums[1]);
        int x = Integer.parseInt(nums[2]);

        nums = sc.nextLine().split("\\s+");

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            Integer num = Integer.parseInt(nums[i]);
            stack.push(num);
        }

        for (int i = 0; i < p; i++) {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        }


        if (!stack.isEmpty()) {
            boolean present = stack.stream().filter(d -> d.equals(x)).findFirst().isPresent();

            if (!present) {
                System.out.println(stack.stream().mapToInt(d -> d).min().getAsInt());
            } else {
                System.out.println("true");
            }

        } else {
            System.out.println(0);
        }
    }

}
