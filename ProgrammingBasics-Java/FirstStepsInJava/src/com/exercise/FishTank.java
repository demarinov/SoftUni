package com.exercise;

import java.util.Scanner;

public class FishTank {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // code calculate the volume of "aquabox", 40% less since not water
        int length = Integer.parseInt(scan.nextLine());
        int width  = Integer.parseInt(scan.nextLine());
        int height = Integer.parseInt(scan.nextLine());
        Double percent = Double.parseDouble(scan.nextLine());

        Double volInLiters = length * width * height / 1000d;

        System.out.printf("%.02f",(volInLiters - percent/100d * volInLiters));


    }
}
