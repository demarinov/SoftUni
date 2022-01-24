package com.exercise;

import java.util.Scanner;

public class FruitMarket {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // raspberries 50% less than strawberry, oranges 40% less than raspb, bananas 80% less than raspb
        double priceStrawb = Double.parseDouble(scan.nextLine());
        double bananas = Double.parseDouble(scan.nextLine());
        double oranges = Double.parseDouble(scan.nextLine());
        double raspb = Double.parseDouble(scan.nextLine());
        double strawb = Double.parseDouble(scan.nextLine());
        double priceRaspb = (priceStrawb - 0.5d * priceStrawb);
        double priceOranges = (priceRaspb - 0.4d * priceRaspb);
        double priceBananas = (priceRaspb - 0.8d * priceRaspb);

        double priceFruits = priceStrawb * strawb + priceRaspb * raspb + priceOranges * oranges
                + priceBananas * bananas;

        System.out.printf("%.02f",priceFruits);
    }
}
