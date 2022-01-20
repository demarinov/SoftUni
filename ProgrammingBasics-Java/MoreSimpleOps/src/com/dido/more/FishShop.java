package com.dido.more;

import java.util.Scanner;

public class FishShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double mack = Double.parseDouble(sc.nextLine());
        double toy = Double.parseDouble(sc.nextLine());
        double kiloBonito = Double.parseDouble(sc.nextLine());
        double kiloHMack = Double.parseDouble(sc.nextLine());
        double kiloMussels = Double.parseDouble(sc.nextLine());


//        •	Паламуд – 60% по-скъп от скумрията
        //•	Сафрид – 80% по-скъп от цацата
        //•	Миди – 7.50 лв. за килограм


        double bonito = mack + (0.6 * mack);
        double hmack = toy + (0.8 * toy);
        double mussels = 7.50;

        double totalPrice = (bonito * kiloBonito) + (hmack * kiloHMack) + (mussels * kiloMussels);

        System.out.printf("%.2f",totalPrice);

    }
}
