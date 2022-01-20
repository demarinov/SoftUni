package com.dido.lab;

import java.util.Scanner;

public class BackIn30Min {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hour = Integer.parseInt(sc.nextLine());
        int min = Integer.parseInt(sc.nextLine());

        int nextHour = hour;
        int nextMin = min + 30;

        if (nextMin > 59) {
            nextHour++;
            nextMin = nextMin - 60;
        }

        if (nextHour > 23) {
            nextHour = nextHour - 24;
        }

        System.out.printf("%d:%02d",nextHour,nextMin);

    }
}
