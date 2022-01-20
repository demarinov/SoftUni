package com.dido.more;

import java.util.Scanner;

public class FootballLeague {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int capacity = Integer.parseInt(sc.nextLine());
        int fans = Integer.parseInt(sc.nextLine());
        int fansA = 0;
        int fansB = 0;
        int fansV = 0;
        int fansG = 0;

        for (int i = 1; i <= fans; i++) {

            String sector = sc.nextLine();

            if (sector.charAt(0) == 'A') {
                fansA++;
            } else if (sector.charAt(0) == 'B') {
                fansB++;
            } else if (sector.charAt(0) == 'V') {
                fansV++;
            } else if (sector.charAt(0) == 'G') {
                fansG++;
            }
        }

        System.out.printf("%.2f%%%n",(100.0 * fansA / fans));
        System.out.printf("%.2f%%%n",(100.0 * fansB / fans));
        System.out.printf("%.2f%%%n",(100.0 * fansV / fans));
        System.out.printf("%.2f%%%n",(100.0 * fansG / fans));
        System.out.printf("%.2f%%%n",(100.0 * fans/capacity));
    }
}
