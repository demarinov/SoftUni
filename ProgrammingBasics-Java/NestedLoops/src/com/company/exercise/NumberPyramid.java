package com.company.exercise;

import java.util.Scanner;

public class NumberPyramid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        // n = 7
        // 1
        // 2 3
        // 4 5 6
        // 7
        int rows = 1;
        int num = 0;

        while(num < n) {

            for (int i = 1; i <= rows; i++) {

                num++;

                System.out.print(num);
                System.out.print(" ");
                if (num == n) {
                    break;
                }


            }

            System.out.println();
            rows++;
        }

    }
}
