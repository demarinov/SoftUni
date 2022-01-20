package com.dido.more;

import java.util.Scanner;

public class FlowersShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numH = Integer.parseInt(sc.nextLine());
        int numR = Integer.parseInt(sc.nextLine());
        int numT = Integer.parseInt(sc.nextLine());
        String season = sc.nextLine();
        String holiday = sc.nextLine();
        double priceH = 0.0d;
        double priceR = 0.0d;
        double priceT = 0.0;
        double totalPrice = 0.0d;
        int totalFlowers = numH + numR + numT;

//        Сезон	Хризантеми	Рози	Лалета
//        Пролет / Лято	2.00 лв./бр.	4.10 лв./бр.	2.50 лв./бр.
//        Есен / Зима	3.75 лв./бр.	4.50 лв./бр.	4.15 лв./бр.

        if (season.equals("Spring") || season.equals("Summer"))
        {
            priceH = 2.00 * numH;
            priceR = 4.1 * numR;
            priceT = 2.5 * numT;

            totalPrice = priceH + priceR + priceT;

            if (holiday.charAt(0) == 'Y') {
                totalPrice += (0.15 * totalPrice);
            }

            if (numT > 7 && season.equals("Spring")) {
                totalPrice -= (0.05 * totalPrice);
            }

            if (totalFlowers > 20) {
                totalPrice -= (0.2 * totalPrice);
            }

            totalPrice += 2.0;

        } else if (season.equals("Autumn") || season.equals("Winter")){
            priceH = 3.75 * numH;
            priceR = 4.50 * numR;
            priceT = 4.15 * numT;

            totalPrice = priceH + priceR + priceT;

            if (holiday.charAt(0) == 'Y') {
                totalPrice += (0.15 * totalPrice);
            }

            if (numR >= 10 && season.equals("Winter")) {
                totalPrice -= (0.10 * totalPrice);
            }

            if (totalFlowers > 20) {
                totalPrice -= (0.2 * totalPrice);
            }

            totalPrice += 2.0;

        }

        System.out.printf("%.2f",totalPrice);



    }
}
