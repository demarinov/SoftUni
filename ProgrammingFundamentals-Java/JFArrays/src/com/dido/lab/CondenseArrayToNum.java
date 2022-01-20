package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class CondenseArrayToNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        while (nums.length > 1) {
            int[] condensed = new int[nums.length - 1];

            for (int i = 0; i < condensed.length; i++) {
                condensed[i] = nums[i] + nums[i+1];
            }

            nums = condensed;
        }

        System.out.printf("%d%n",nums[0]);
    }
}
