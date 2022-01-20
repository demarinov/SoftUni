package com.dido.lab;

import java.util.Scanner;

public class EvenNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        int rem = num % 2;

        while (rem != 0) {

            System.out.println("Please write an even number.");

            num = Integer.parseInt(sc.nextLine());
            rem = num % 2;
        }

        System.out.printf("The number is: %d",Math.abs(num));
    }
}
