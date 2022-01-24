package com.exercise;

import java.util.Scanner;

public class Birthday {

    public static void main(String[] args) {
        // code to calculate the birthday party price

        Scanner scan = new Scanner(System.in);

        double rent = Double.parseDouble(scan.nextLine());

        // cake 20%, drinks 45% less than cake, animator 1/3 of rent
        double cakePrice = 0.2d * rent;
        double drinksPrice = cakePrice - 0.45d * cakePrice;
        double animator = rent/3d;

        double totalPrice = rent + cakePrice + drinksPrice + animator;

        System.out.println(totalPrice);
    }
}
