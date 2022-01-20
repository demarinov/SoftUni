package com.company.exercise;

import java.util.Scanner;

public class Coins2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // calc min number of coins to return for a sum as a change

        double n = Double.parseDouble(sc.nextLine());
        // possible coin values 2.00, 1.00, 0.5, 0.2, 0.1, 0.05, 0.02, 0.01 - div by 2
        double coinValue = 2.00d;
        double num = n;
        int numOfCoins = 0;

        if (num < 0.01) {
            return;
        }

        while (num > 0) {

            double roundedDiff = 1.0 * Math.round((num - coinValue) * 100)/100;
            double remainingValue = Math.floor(roundedDiff * 100) / 100;

            if (remainingValue < 0) {
                // try different coin value

                if (coinValue >= 0 && coinValue <= 0.1) {
                    coinValue = Math.floor((coinValue * 100) / 2) / 100;
                } else if (coinValue > 0.1 && coinValue < 1) {
                    coinValue = Math.floor((coinValue * 10) / 2) / 10;
                } else if (coinValue >= 1) {
                    coinValue /= 2;
                }


                if (coinValue <= 0.01) {
                    numOfCoins++;
                    break;
                }
                continue;
            }

            num = remainingValue;
            numOfCoins++;

        }

        System.out.println(numOfCoins);
    }
}
