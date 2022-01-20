package com.company.exercise;

import java.util.Scanner;

public class Volleyball {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String year = sc.nextLine();
        int vacations = Integer.parseInt(sc.nextLine());
        int weekendsGoHome = Integer.parseInt(sc.nextLine());
        // total yearly weekends 48, 3/4 of sofia weekends used for volleyball
        double weekendsInSofia =  (48 - weekendsGoHome ) * 3d/4d;
        double vacationsPlay = vacations * 2d/3d;

        double totalPlayTime = weekendsGoHome + weekendsInSofia + vacationsPlay;

        // Leap years - plays 15% more
        if (year.equals("leap")) {
            totalPlayTime = totalPlayTime * (1 + 0.15d);
        }

        System.out.printf("%.0f",Math.floor(totalPlayTime));



    }
}
