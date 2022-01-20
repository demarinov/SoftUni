package com.dido.exercise;

import java.util.Scanner;

public class NxN {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        printNxNMatrix(n);
    }

    public static void printNxNMatrix(int n) {

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= n; j++) {

                System.out.printf("%d ",n);
            }
            System.out.println();

        }
    }
}
