package com.company.lab;

import java.util.Scanner;

public class FruitShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fruit = sc.nextLine();
        String day = sc.nextLine();
        double q = Double.parseDouble(sc.nextLine());

        double totalPrice = 0.0d;

        switch(day) {


            case "Monday":
            case "Tuesday":
            case "Wednesday":
            case "Thursday":
            case "Friday":

                // 2.50	1.20	0.85	1.45	2.70	5.50	3.85
                if (fruit.equals("banana")) {
                    totalPrice = q * 2.5;
                } else if (fruit.equals("apple")) {
                    totalPrice = q * 1.20;
                } else if (fruit.equals("orange")) {
                    totalPrice = q * 0.85;
                } else if (fruit.equals("grapefruit")) {
                    totalPrice = q * 1.45;
                } else if (fruit.equals("kiwi")) {
                    totalPrice = q * 2.70;
                } else if (fruit.equals("pineapple")) {
                    totalPrice = q * 5.50;
                } else if (fruit.equals("grapes")) {
                    totalPrice = q * 3.85;
                }
                break;

            case "Saturday":
            case "Sunday":

                // 2.70	1.25	0.90	1.60	3.00	5.60	4.20
                if (fruit.equals("banana")) {
                    totalPrice = q * 2.70;
                } else if (fruit.equals("apple")) {
                    totalPrice = q * 1.25;
                } else if (fruit.equals("orange")) {
                    totalPrice = q * 0.9;
                } else if (fruit.equals("grapefruit")) {
                    totalPrice = q * 1.60;
                } else if (fruit.equals("kiwi")) {
                    totalPrice = q * 3;
                } else if (fruit.equals("pineapple")) {
                    totalPrice = q * 5.60;
                } else if (fruit.equals("grapes")) {
                    totalPrice = q * 4.20;
                }
                break;
            default:
                break;

        }

        if (totalPrice > 0) {
            System.out.printf("%.2f",totalPrice);
        } else {
            System.out.println("error");
        }

    }
}
