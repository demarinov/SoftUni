package com.company.lab;

import java.util.Scanner;

public class EvenPowersBy2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int res = 2;

        System.out.println(1);
        for (int i = 1; i <= n; i++) {

            if (i % 2 == 0) {

                System.out.println(res);
            }
            res *= 2;
        }
    }
}
