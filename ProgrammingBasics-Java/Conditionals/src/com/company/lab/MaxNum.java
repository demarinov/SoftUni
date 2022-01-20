package com.company.lab;

import java.util.Scanner;

public class MaxNum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());

        if (n1 > n2) {
            System.out.println(n1);
        } else {
            System.out.println(n2);
        }
    }
}
