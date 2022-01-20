package com.dido.lab;

import java.util.Scanner;

public class Orders {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String product = sc.nextLine();
        int quantity = Integer.parseInt(sc.nextLine());

        printProductTotalPrice(product, quantity);
    }

    public static void printProductTotalPrice(String prod, int q) {
//•	coffee – 1.50
//•	water – 1.00
//•	coke – 1.40
//•	snacks – 2.00
        double price = 0.0d;

        switch(prod) {
            case "coffee":
                price = 1.50;
                break;
            case "water":
                price= 1.00;
                break;
            case "coke" :
                price = 1.40;
                break;
            case "snacks":
                price= 2.00;
                break;
            default:
                break;
        }

        System.out.printf("%.2f",(price * q));
    }
}
