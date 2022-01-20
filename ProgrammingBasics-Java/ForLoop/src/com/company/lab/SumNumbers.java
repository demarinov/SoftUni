package com.company.lab;

import java.util.Scanner;

public class SumNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int sum = 0;

        for (int i = 1; i <= n; i++) {

            int num = Integer.parseInt(sc.nextLine());
            sum += num;
        }

        System.out.println(sum);
    }
}
