package com.company.lab;

import java.util.Scanner;

public class Combinations {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int solutions = 0;
        int n = Integer.parseInt(sc.nextLine());

        for (int x1 = 0; x1 <=n; x1++) {
            for (int x2 = 0; x2 <=n; x2++) {
                for (int x3 = 0; x3 <=n; x3++) {

                    if (n == (x1 + x2 + x3)) {
                        solutions++;
                    }
                }
            }
        }

        System.out.println(solutions);
    }
}
