package com.dido.exercise;

import java.util.Scanner;

public class SmallestNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        int n3 = Integer.parseInt(sc.nextLine());

        System.out.println(getSmallestNumber(n1,n2,n3));
    }

    public static int getSmallestNumber(int n1, int n2, int n3) {

        int min = n1;

        if (min > n2) {
            min = n2;
        }

        if (min > n3) {
            min = n3;
        }

        return min;
    }
}
