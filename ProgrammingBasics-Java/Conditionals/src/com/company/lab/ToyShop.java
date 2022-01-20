package com.company.lab;

import java.util.Scanner;

public class ToyShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double priceExcursion = Double.parseDouble(sc.nextLine());
        int puzzles = Integer.parseInt(sc.nextLine());
        int dolls = Integer.parseInt(sc.nextLine());
        int bears = Integer.parseInt(sc.nextLine());
        int minions = Integer.parseInt(sc.nextLine());
        int trucks = Integer.parseInt(sc.nextLine());

        //prices: puzzles 2.6, dolls 3, bears 4.1, minion 8.2, truck 2

        double totalPrice = (puzzles * 2.6d) + (dolls * 3) + (bears * 4.1)
                + (minions * 8.2) + (trucks * 2);
        int numOfToys = puzzles + dolls + bears + minions + trucks;

        if (numOfToys >= 50 ) {
            totalPrice = totalPrice * (1 - 0.25d);

        }

        // 10% rent payment
        double finalPrice = totalPrice - totalPrice * 0.1d;
        double priceDiff = finalPrice - priceExcursion;

        if (priceDiff >=0) {
            System.out.printf("%s%.2f%s","Yes! ",Math.abs(priceDiff)," lv left.");
        } else {
            System.out.printf("%s%.2f%s","Not enough money! ",Math.abs(priceDiff)," lv needed.");
        }
    }
}
