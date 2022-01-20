package com.dido.more;

import java.util.Scanner;

public class FuelHolder2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fuelType = sc.nextLine();
        int fuel = Integer.parseInt(sc.nextLine());
        String clubCard = sc.nextLine();

//          •	Бензин – 2.22 лева за един литър,
//          •	Дизел – 2.33 лева за един литър
//          •	Газ – 0.93 лева за литър
        double price = 0.0d;

        switch(fuelType) {

            case "Gas":


                if (clubCard.equals("Yes")) {
                    price = (0.93 - 0.08) * fuel;
                } else {
                    price = 0.93 * fuel;
                }
                break;
            case "Gasoline":

                if (clubCard.equals("Yes")) {
                    price = (2.22 - 0.18) * fuel;
                } else {
                    price = 2.22 * fuel;
                }
                break;
            case "Diesel":

                if (clubCard.equals("Yes")) {
                    price = (2.33 - 0.12) * fuel;
                } else {
                    price = 2.33 * fuel;
                }
                break;
            default:
                break;
        }

        if (fuel >= 20 && fuel <= 25) {
            price -= (0.08 * price);
        } else if (fuel > 25) {
            price -= (0.1 * price);
        }

        System.out.printf("%.2f lv.",price);
    }
}
