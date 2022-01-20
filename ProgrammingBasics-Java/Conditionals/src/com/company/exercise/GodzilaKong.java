package com.company.exercise;

import java.util.Scanner;

public class GodzilaKong {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double budget = Double.parseDouble(sc.nextLine());
        int countPeople = Integer.parseInt(sc.nextLine());
        double priceClothes = Double.parseDouble(sc.nextLine());

        double totalPriceClothes = priceClothes * countPeople;
        double priceDecor = 0.1d * budget;

        if (countPeople > 150) {
            totalPriceClothes = totalPriceClothes * (1 - 0.1d);
        }

        double priceDiff = budget - (totalPriceClothes + priceDecor);

        if (priceDiff < 0) {
            System.out.println("Not enough money!");
            System.out.printf("%s%.2f%s","Wingard needs ",
                    (Math.abs(priceDiff)),
                    " leva more.");
        } else {
            System.out.println("Action!");
            System.out.printf("%s%.2f%s","Wingard starts filming with ",
                    priceDiff,
                    " leva left.");
        }
    }
}
