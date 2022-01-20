package com.company.lab;

import java.util.Scanner;

public class OddEvenSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int evenSum = 0, oddSum = 0, num = 0;

        for (int i = 1; i <= n; i++) {

            if (i % 2 == 0) {
                // even
                num = Integer.parseInt(sc.nextLine());
                evenSum += num;
            } else {
                // odd
                num = Integer.parseInt(sc.nextLine());
                oddSum += num;
            }
        }

        if (evenSum == oddSum) {
            System.out.println("Yes");
            System.out.println("Sum = " + evenSum);
        } else {
            System.out.println("No");
            System.out.println("Diff = "+ (Math.abs(evenSum - oddSum)));
        }
    }
}
