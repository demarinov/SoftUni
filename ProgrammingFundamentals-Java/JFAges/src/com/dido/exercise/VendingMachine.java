package com.dido.exercise;

import java.util.Scanner;

public class VendingMachine {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        double sumIn = 0.0d;

        while(!input.equals("Start")) {

            double amount = Double.parseDouble(input);

            if (amount != 0.1 && amount != 0.2
                    && amount != 0.5 && amount != 1 && amount != 2) {
                System.out.printf("Cannot accept %.2f%n",amount);
                input =sc.nextLine();
                continue;
            }

            sumIn += amount;

            input =sc.nextLine();
        }

        input =sc.nextLine();


        while(!input.equals("End")) {

            String prod = input;
            double price = 0.0d;
            boolean invalidProduct = false;

            switch(prod) {

                case "Nuts":
                    price = 2.0;
                    break;
                case "Water":
                    price = 0.7;
                    break;
                case "Crisps":
                    price = 1.5;
                    break;
                case "Soda":
                    price = 0.8;
                    break;
                case "Coke":
                    price = 1.0;
                    break;
                default:
                    System.out.printf("Invalid product%n");
                    invalidProduct = true;
                    break;
            }

            if (price > sumIn) {
                System.out.printf("Sorry, not enough money%n");
            } else if (!invalidProduct) {
                System.out.printf("Purchased %s%n",prod);
                sumIn -= price;
            }

            input = sc.nextLine();
        }

        System.out.printf("Change: %.2f",sumIn);

    }
}
