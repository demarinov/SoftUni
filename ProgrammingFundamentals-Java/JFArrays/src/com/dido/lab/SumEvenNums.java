package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class SumEvenNums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        int sumEven = 0;

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] % 2 == 0) {
                sumEven += nums[i];
            }
        }

        System.out.println(sumEven);
    }
}
