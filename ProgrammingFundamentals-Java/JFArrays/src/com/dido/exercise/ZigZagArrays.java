package com.dido.exercise;

import java.util.Scanner;

public class ZigZagArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] ar1 = new int[n];
        int[] ar2 = new int[n];

        for (int i = 1; i <= n; i++) {

            String[] nums = sc.nextLine().split(" ");


            if (i % 2 == 0) {
                ar1[i-1] = Integer.parseInt(nums[1]);
                ar2[i-1] = Integer.parseInt(nums[0]);
            } else {
                ar1[i-1] = Integer.parseInt(nums[0]);;
                ar2[i-1] = Integer.parseInt(nums[1]);;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.printf("%d ",ar1[i]);
        }

        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.printf("%d ",ar2[i]);
        }
    }
}
