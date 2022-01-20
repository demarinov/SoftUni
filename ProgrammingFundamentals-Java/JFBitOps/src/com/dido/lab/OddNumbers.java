package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class OddNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();


        int result = 0;
        // xor result with all alements of array
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }

        System.out.println(result);
    }
}
