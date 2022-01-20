package com.dido.lab;

import java.util.Scanner;

public class PrintInReverse {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {

            nums[i] = Integer.parseInt(sc.nextLine());
        }

        for (int i = n-1; i >=0; i--) {

            System.out.printf("%d ",nums[i]);
        }
    }
}
