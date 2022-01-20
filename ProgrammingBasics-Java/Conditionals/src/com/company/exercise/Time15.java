package com.company.exercise;

import java.util.Scanner;

public class Time15 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hour = Integer.parseInt(sc.nextLine());
        int minutes = Integer.parseInt(sc.nextLine());

        int newHour = hour;
        int newMinutes = minutes + 15;

        if (newMinutes > 59) {
            newMinutes = newMinutes - 60;
            newHour = newHour + 1;
        }

        if (newHour >= 24) {
            newHour = newHour - 24;
        }

        System.out.printf("%d:%02d",newHour, newMinutes);

    }
}
