package com.dido.more;

import java.util.Scanner;

public class Harvest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = Integer.parseInt(sc.nextLine());
        double y = Double.parseDouble(sc.nextLine());
        int z = Integer.parseInt(sc.nextLine());
        int workers = Integer.parseInt(sc.nextLine());

        double wineKilos = y * (0.4 * x);
        double wineLiters = wineKilos / 2.5;

        if (wineLiters >= z) {
            double wineLeft = wineLiters - z;

            System.out.printf("Good harvest this year! Total wine: %.0f liters.%n",Math.floor(wineLiters));
            System.out.printf("%.0f liters left -> %.0f liters per person.",wineLeft, Math.ceil(wineLeft/workers));
        } else {
            System.out.printf("It will be a tough winter! More %.0f liters wine needed.",Math.floor(z - wineLiters));
        }
    }
}
