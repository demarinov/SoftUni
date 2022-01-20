package com.dido.exams1;

import java.util.Scanner;

public class ComputerStore {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Double totalPrice = 0.0d;
        while(!input.equals("special") && !input.equals("regular")) {

            Double price = Double.parseDouble(input);

            if (price < 0) {
                System.out.println("Invalid price!");
            } else {
                totalPrice += price;
            }

            input = sc.nextLine();
        }

//        The taxes are 20% of each part's price you receive.
//        If the customer is special, then he has a 10% discount of the price of the total price with taxes.


        if (totalPrice > 0) {

            Double totalPriceWithoutTax = totalPrice;
            Double taxes = 0.20 * totalPrice;
            totalPrice += taxes;

            if (input.equals("special")) {

                totalPrice -= totalPrice * 0.10;
            }

            System.out.printf("Congratulations you've just bought a new computer!\n" +
                    "Price without taxes: %.2f$\n" +
                    "Taxes: %.2f$\n" +
                    "-----------\n" +
                    "Total price: %.2f$\n",totalPriceWithoutTax, taxes, totalPrice);

        } else {
            System.out.println("Invalid order!");
        }


    }
}
