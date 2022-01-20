package com.dido.lab;

import java.util.Scanner;

public class MultiTab2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());

        if (m > 10) {
            System.out.printf("%d X %d = %d",n,m,(n * m));
        } else {
            for (int i = m; i <= 10; i++) {

                System.out.printf("%d X %d = %d%n",n,i,(n * i));
            }
        }
    }
}
