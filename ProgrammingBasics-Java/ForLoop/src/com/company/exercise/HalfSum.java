package com.company.exercise;

import java.util.Scanner;

public class HalfSum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int maxNum = Integer.MIN_VALUE, maxNumSum = 0;

        for (int i = 1; i <= n; i++) {

            int num = Integer.parseInt(sc.nextLine());

            maxNumSum += num;

            if (num > maxNum) {
                if (i > 1) {
                    maxNumSum += maxNum;
                }
                maxNum = num;
                maxNumSum -= num;
            }

        }

        if (maxNum == maxNumSum) {

            System.out.println("Yes");
            System.out.println("Sum = "+ maxNum);
        } else {
            System.out.println("No");
            System.out.println("Diff = "+ (Math.abs(maxNum - maxNumSum)));
        }
    }
}
