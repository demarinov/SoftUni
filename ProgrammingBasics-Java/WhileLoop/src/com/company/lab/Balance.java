package com.company.lab;

import java.util.Scanner;

public class Balance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        double balance = 0;
        String input = sc.nextLine();

        while(!input.equals("NoMoreMoney")) {

            double amount = Double.parseDouble(input);

            if (amount < 0) {
                System.out.println("Invalid operation!");
                break;
            }

            System.out.printf("Increase: %.2f%n",amount);
            balance += amount;
            input = sc.nextLine();
        }

        System.out.printf("Total: %.2f",balance);
    }
}
