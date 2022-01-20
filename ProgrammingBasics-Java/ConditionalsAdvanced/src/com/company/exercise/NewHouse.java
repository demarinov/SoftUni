package com.company.exercise;

import java.util.Scanner;

public class NewHouse {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String flowerType = sc.nextLine();
        int qty = Integer.parseInt(sc.nextLine());
        int budget = Integer.parseInt(sc.nextLine());

        double flowerPrice = 0.0d;

        switch(flowerType) {

            case "Roses":
                // price 5
                flowerPrice = qty * 5.0d;

                if (qty > 80) {
                    // 10% discount
                    flowerPrice = flowerPrice * (1 - 0.1d);
                }

                break;
            case "Dahlias":
                // 3.8
                flowerPrice = qty * 3.8d;
                if (qty > 90) {
                    // 15 % discount
                    flowerPrice = flowerPrice * (1 - 0.15d);
                }

                break;

            case "Tulips":
                // 2.8
                flowerPrice = qty * 2.8d;
                if (qty > 80) {
                    // 15% discount
                    flowerPrice = flowerPrice * (1 - 0.15d);
                }

                break;
            case "Narcissus":
                // 3
                flowerPrice = qty * 3.0d;
                if (qty < 120) {
                    // 15% rise
                    flowerPrice = flowerPrice * (1 + 0.15d);
                }


                break;

            case "Gladiolus":
                // 2.5
                flowerPrice = qty * 2.5d;
                if (qty < 80) {
                    // 20% rise
                    flowerPrice = flowerPrice * (1 + 0.20d);
                }

                break;

            default:

                return;
        }

        double budgetDiff =  budget - flowerPrice;

        if (budgetDiff >= 0) {
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left."
                    ,qty, flowerType, budgetDiff);
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.",Math.abs(budgetDiff));
        }


    }
}
