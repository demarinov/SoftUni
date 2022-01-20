package com.dido.lab;

import java.util.Scanner;

public class Fixing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] weekDays = new String[5];

        weekDays[0] = "Monday";
        weekDays[1] = "Tuesday";
        weekDays[2] = "Wednesday";
        weekDays[3] = "Thursday";
        weekDays[4] = "Friday";

        try {
            for (int i = 0; i <= weekDays.length; i++) {
                System.out.println(weekDays[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {

        }


        sc.nextLine();
    }
}
