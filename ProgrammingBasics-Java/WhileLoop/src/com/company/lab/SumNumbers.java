package com.company.lab;

import java.util.Scanner;

public class SumNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int targetSum = Integer.parseInt(sc.nextLine());
        int sum = Integer.parseInt(sc.nextLine());

        while(sum < targetSum) {

            sum += Integer.parseInt(sc.nextLine());
        }

        System.out.println(sum);
    }
}
