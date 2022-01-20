package com.company.lab;

import java.util.Scanner;

public class Seq2kPlus1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int i = 1;

        while(i <= n) {

            System.out.println(i);
            i = i*2 + 1;
        }

    }
}
