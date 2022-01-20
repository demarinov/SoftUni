package com.company.lab;

import java.util.Scanner;

public class NumberSequence {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {

            int num = Integer.parseInt(sc.nextLine());

            if (num > maxNum) {
                maxNum = num;
            }

            if (num < minNum) {
                minNum = num;
            }

        }

        System.out.println("Max number: "+maxNum);
        System.out.println("Min number: "+minNum);


    }
}
