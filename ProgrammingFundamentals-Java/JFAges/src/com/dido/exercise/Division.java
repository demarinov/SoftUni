package com.dido.exercise;

import java.util.Scanner;

public class Division {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        // 2 3 6 7 10

        int div = 0;
        if (num % 10 == 0) {
            div = 10;
        } else if (num % 7 == 0) {
            div = 7;
        } else if (num % 6 == 0) {
            div = 6;
        } else if (num % 3 == 0) {
            div = 3;
        } else if (num % 2 == 0) {
            div = 2;
        }

        if (div != 0) {
            System.out.printf("The number is divisible by %d",div);
        } else {
            System.out.print("Not divisible");
        }
    }
}
