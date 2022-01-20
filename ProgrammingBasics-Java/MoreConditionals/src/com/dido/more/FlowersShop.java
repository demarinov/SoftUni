package com.dido.more;

import java.util.Scanner;

public class FlowersShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int m = Integer.parseInt(sc.nextLine());
        int z = Integer.parseInt(sc.nextLine());
        int r = Integer.parseInt(sc.nextLine());
        int c = Integer.parseInt(sc.nextLine());
        double giftPrice = Double.parseDouble(sc.nextLine());

//        •	Магнолии – 3.25 лева
//        •	Зюмбюли – 4 лева
//        •	Рози – 3.50 лева
//        •	Кактуси – 8 лева

        double totalFPrice = 3.25 * m + 4.0 * z + 3.5 * r + 8.0 * c;
//        От общата сума, Мария трябва да плати 5% данъци.
        totalFPrice -= (0.05 * totalFPrice);

        if (totalFPrice >= giftPrice) {
            System.out.printf("She is left with %d leva.",(int) Math.floor(totalFPrice - giftPrice));
        } else {
            System.out.printf("She will have to borrow %d leva.",(int) Math.ceil(giftPrice - totalFPrice));
        }

    }
}
