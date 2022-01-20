package com.dido.more;

import java.util.Scanner;

public class SleepyCatTom {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int restDays = Integer.parseInt(sc.nextLine());

        int nonRestDays = 365 - restDays;

//        •	Когато е на работа, стопанинът му си играе с него по 63 минути на ден.
//        •	Когато почива, стопанинът му си играе с него  по 127 минути на ден.

        int playTime = 63 * nonRestDays + 127 * restDays;

        // cat norm 30 000 to play


        if (playTime > 30000) {
            int hours = (playTime - 30000) / 60;
            int minutes = (playTime - 30000) % 60;
            System.out.println("Tom will run away");
            System.out.printf("%d hours and %d minutes more for play",hours,minutes);
        } else {
            int hours = (30000 - playTime) / 60;
            int minutes = (30000 - playTime) % 60;
            System.out.println("Tom sleeps well");
            System.out.printf("%d hours and %d minutes less for play",hours,minutes);
        }

    }
}
