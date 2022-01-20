package com.dido.more;

import java.util.Scanner;

public class VegetableMarket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double priceVegs = Double.parseDouble(sc.nextLine());
        double priceFruits = Double.parseDouble(sc.nextLine());
        int vegs = Integer.parseInt(sc.nextLine());
        int fruits = Integer.parseInt(sc.nextLine());

        // 1euro = 1.94 lv.

        double totalEuro = (priceFruits * fruits + priceVegs * vegs)/ 1.94;

        System.out.printf("%.2f",totalEuro);
    }
}
