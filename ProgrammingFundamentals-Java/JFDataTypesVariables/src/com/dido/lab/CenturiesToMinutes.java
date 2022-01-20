package com.dido.lab;

import java.util.Scanner;

public class CenturiesToMinutes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int centu = Integer.parseInt(sc.nextLine());
        int years = centu * 100;
        int days = (int)(years * 365.2422);
        int hours = days * 24;
        int minutes = hours * 60;

        System.out.printf("%d centuries = " +
                "%d years = %d days = %d hours = %d minutes",centu,years,days, hours,minutes);
    }
}
