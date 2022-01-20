package com.dido.exercise;

import java.util.Scanner;

public class SumDigits {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        int rem = 0;
        int sum = 0;

        while (num > 0) {

            rem = num % 10;
            sum += rem;
            num /= 10;

        }

        System.out.printf("%d",sum);
    }
}
