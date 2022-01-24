package com.exercise;

import java.util.Scanner;

public class Charity {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // cake 45, waffles 5.8, pancake 3.2, cost 1/8 of total price

        int days = Integer.parseInt(scan.nextLine());
        int pCookCount = Integer.parseInt(scan.nextLine());
        int cakesPerDayCook = Integer.parseInt(scan.nextLine());
        int wafflesPerDayCook = Integer.parseInt(scan.nextLine());
        int pancakesPerDayCook = Integer.parseInt(scan.nextLine());

        double sum = (pCookCount * days) *
                (cakesPerDayCook * 45d + wafflesPerDayCook * 5.8d + pancakesPerDayCook * 3.2d);
        double cost = sum / 8d;

        System.out.printf("%.02f",sum-cost);

    }
}
