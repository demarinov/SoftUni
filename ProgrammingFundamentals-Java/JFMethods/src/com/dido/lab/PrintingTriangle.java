package com.dido.lab;

import java.util.Scanner;

public class PrintingTriangle {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
//Input	  Output
//3       1
//        1 2
//        1 2 3
//        1 2
//        1

        printTriangle(n);

    }

    public static void printTriangle(int n) {
        // first half
        for (int i = 1; i <=n; i++) {

            printLine(1,i);
            System.out.println();
        }

        // second half
        for (int i = 1; i <= n-1; i++) {
            printLine(1,(n-i));
            System.out.println();
        }
    }

    public static void printLine(int start, int end) {
        for (int j = start; j <= end; j++) {

            System.out.printf("%d ",j);
        }
    }
}
