package com.dido.lab;

import java.util.Scanner;

public class TheatrePromos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String day = sc.nextLine();
        int age = Integer.parseInt(sc.nextLine());

//        Day / Age	0 <= age <= 18	18 < age <= 64	64 < age <= 122
//        Weekday	12$	18$	12$
//        Weekend	15$	20$	15$
//        Holiday	5$	12$	10$

        int price = 0;
        boolean outOfAge = false;

        if (day.equals("Weekday")) {
            if (age >= 0 && age <= 18) {
                price = 12;
            } else if (age > 18 && age <= 64) {
                price = 18;
            } else if (age > 64 && age<= 122) {
                price = 12;
            } else {
                outOfAge = true;
            }
        } else if (day.equals("Weekend")) {
            if (age >= 0 && age <= 18) {
                price = 15;
            } else if (age > 18 && age <= 64) {
                price = 20;
            } else if (age > 64 && age<= 122) {
                price = 15;
            } else {
                outOfAge = true;
            }
        } else if (day.equals("Holiday")) {
            if (age >= 0 && age <= 18) {
                price = 5;
            } else if (age > 18 && age <= 64) {
                price = 12;
            } else if (age > 64 && age<= 122) {
                price = 10;
            } else {
                outOfAge = true;
            }
        }

        if (outOfAge) {
            System.out.printf("Error!");
        } else {
            System.out.printf("%d$",price);
        }

    }
}
