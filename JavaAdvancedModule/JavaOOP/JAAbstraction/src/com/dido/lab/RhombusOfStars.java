package com.dido.lab;

import java.util.Scanner;

public class RhombusOfStars {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

//        printStarsOne(n);
        printStarsTwo(n);
    }

    public static void printStarsTwo(int n) {
        for (int i = 1; i <= n; i++) {
            printRow(" ",n-i);
            printRow("*",1);
            printRow(" *",i-1);
            System.out.println();
        }

        for (int i = 1; i <= n-1; i++) {
            printRow(" ",i);
            printRow("*",1);
            printRow(" *", n-i-1);
            System.out.println();
        }
    }

    public static void printStarsOne(int n) {
        for (int i = 1; i <= n; i++) {
            printRow(" ",n-i);
            printRow("* ",i);
            System.out.println();
        }

        for (int i = 1; i <= n-1; i++) {
            printRow(" ",i);
            printRow("* ", n-i);
            System.out.println();
        }
    }

    public static void printRow(String element, int times) {

        for (int i = 0; i < times; i++) {
            System.out.printf("%s",element);
        }
    }
}
