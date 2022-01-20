package com.company.lab;

import java.util.Scanner;

public class CleverLily {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int age = Integer.parseInt(sc.nextLine());
        double priceWM = Double.parseDouble(sc.nextLine());
        Integer priceToy = Integer.parseInt(sc.nextLine());
        double moneyEven = 0.0d, moneyOdd = 0.0d;
        int oddNum = 0;
        double totalMoney = 0;
        int moneyStep = 10;

        for (int i = 1; i <= age; i++) {

            if (i % 2 == 0) {
                moneyEven += moneyStep;
                moneyEven -= 1;
                moneyStep += 10;
            } else {
                oddNum++;
            }
        }

        moneyOdd = 1.0d * oddNum * priceToy;

        totalMoney = moneyEven + moneyOdd;

        if (totalMoney >= priceWM) {
            System.out.printf("Yes! %.2f",(totalMoney - priceWM));

        } else {
            System.out.printf("No! %.2f",(priceWM - totalMoney));
        }
    }
}
