package com.dido.exercise;

import java.util.Scanner;

public class Elevator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        int count = 0;

        if (n % p == 0){
            count = n/p;
        } else {
            count = n/p + 1;
        }

        // count = (int) Math.ceil((double) n/p);

        System.out.printf("%d",count);
    }
}
