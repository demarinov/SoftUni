package com.company.lab;

import java.util.Scanner;

public class MultiplicationTable {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <=10; i++) {

            for (int j = 1; j <= 10; j++) {

                System.out.printf("%d * %d = %d%n",i,j,(i * j));
            }
        }
    }
}
