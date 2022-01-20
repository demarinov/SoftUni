package com.dido.lab;

import java.util.Scanner;

public class SumOfOdds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());

        int sumOdd = 0;
        int countOdd = 0;
        int num = 1;

        while (countOdd < n) {

            if (num % 2 != 0) {
                System.out.println(num);
                countOdd++;
                sumOdd += num;
            }

            num++;

        }

        System.out.printf("Sum: %d",sumOdd);

    }
}
