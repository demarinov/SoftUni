package com.dido.lab;

import java.util.Scanner;

public class SpecialNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {

            int num = i;

            int sum = 0;
            int rem = 0;
            while(num > 0) {

                rem = num % 10;
                sum += rem;
                num /= 10;
            }

            boolean isSpecial = (sum == 5 || sum == 7 || sum == 11);
            System.out.printf("%d -> %s%n",i,(isSpecial ? "True" : "False"));
        }
    }
}
