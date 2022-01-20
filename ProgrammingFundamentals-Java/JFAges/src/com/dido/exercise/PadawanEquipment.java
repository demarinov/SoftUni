package com.dido.exercise;

import java.util.Scanner;

public class PadawanEquipment {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        •	The amount of money Ivan Cho has – floating-point number in range [0.00…1,000.00]
//•	The count of students – integer in range [0…100]
//•	The price of lightsabers for a single sabre – floating-point number in range [0.00…100.00]
//•	The price of robes for a single robe – floating-point number in range [0.00…100.00]
//•	The price of belts for a single belt – floating-point number in range [0.00…100.00]

        double moneyHas = Double.parseDouble(sc.nextLine());
        int students = Integer.parseInt(sc.nextLine());
        double priceLightS = Double.parseDouble(sc.nextLine());
        double priceRobes = Double.parseDouble(sc.nextLine());
        double priceBelts = Double.parseDouble(sc.nextLine());

        int freeBelts = students / 6;

        double totalPrice = priceLightS * (students + Math.ceil(0.10 * students))
                + (priceRobes * students) + (priceBelts * (students - freeBelts));

        if (totalPrice <= moneyHas) {
            System.out.printf("The money is enough - it would cost %.2flv.",totalPrice);
        } else {
            System.out.printf("Ivan Cho will need %.2flv more.",(totalPrice - moneyHas));
        }

    }
}
