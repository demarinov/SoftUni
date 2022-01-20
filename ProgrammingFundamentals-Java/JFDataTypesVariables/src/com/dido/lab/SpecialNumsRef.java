package com.dido.lab;

import java.util.Scanner;

public class SpecialNumsRef {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= n; i++) {
            int num = i;
            int sum = 0;
            boolean isSpecial = false;

            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
            isSpecial = (sum == 5) || (sum == 7) || (sum == 11);
            System.out.printf("%d -> %s%n", i, isSpecial ? "True" : "False");
        }

    }
}
