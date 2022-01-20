package com.company.lab;

import java.util.Scanner;

public class LeftRightSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int leftSum = 0, rightSum = 0, num = 0;

        for (int i = 1; i <=2*n; i++) {

            if (i <= n) {
                // leftSum calc.
                num = Integer.parseInt(sc.nextLine());
                leftSum += num;
            } else {
                num = Integer.parseInt(sc.nextLine());
                rightSum += num;
            }

        }

        if (leftSum == rightSum) {
            System.out.println("Yes, sum = "+leftSum);
        } else {
            System.out.println("No, diff = "+(Math.abs(leftSum - rightSum)));
        }
    }
}
