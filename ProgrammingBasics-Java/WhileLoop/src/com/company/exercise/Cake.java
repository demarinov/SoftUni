package com.company.exercise;

import java.util.Scanner;

public class Cake {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int w = Integer.parseInt(sc.nextLine());
        int l = Integer.parseInt(sc.nextLine());
        int cakeSize = w * l;
        int cakeDiff = cakeSize;

        String input = sc.nextLine();

        while (!input.equals("STOP")) {

            cakeDiff = cakeDiff - Integer.parseInt(input);

            if (cakeDiff < 0) {
                System.out.println("No more cake left! You need " +
                        Math.abs(cakeDiff) +
                        " pieces more.");
                break;
            }

            input = sc.nextLine();

        }

        if (cakeDiff >= 0) {
            System.out.println(Math.abs(cakeDiff) +
                    " pieces are left.");
        } else if (cakeDiff == cakeSize) {
            System.out.println(cakeSize +
                    " pieces are left.");
        }
    }
}
