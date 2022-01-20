package com.dido.more;

import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class GamingStore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double balance = Double.parseDouble(sc.nextLine());


//        OutFall 4	$39.99
//        CS: OG	$15.99
//        Zplinter Zell	$19.99
//        Honored 2	$59.99
//        RoverWatch	$29.99
//        RoverWatch Origins Edition	$39.99

        double moneySpent = 0.0d;

        String input = sc.nextLine();

        while (!input.equals("Game Time")) {

            String game = input;
            double price = 0.0d;

            if (game.equals("OutFall 4")) {
                price = 39.99;
            } else if (game.equals("CS: OG")) {
                price = 15.99;
            } else if (game.equals("Zplinter Zell")) {
                price = 19.99;
            } else if (game.equals("RoverWatch")) {
                price = 29.99;
            } else if (game.equals("Honored 2")) {
                price = 59.99;
            } else if (game.equals("RoverWatch Origins Edition")) {
                price = 39.99;
            } else {
                System.out.println("Not Found");
                input = sc.nextLine();
                continue;
            }

            if (price > balance) {
                System.out.println("Too Expensive");
                input = sc.nextLine();
                continue;
            }


            balance -= price;
            moneySpent += price;
            System.out.println("Bought " + game);

            if (balance == 0) {
                System.out.println("Out of money!");
            }

            input = sc.nextLine();

        }

        if (balance > 0) {
            System.out.printf("Total spent: $%.2f. ", moneySpent);
            System.out.printf("Remaining: $%.2f", balance);
        }


    }

}
