package com.dido.more;

import java.util.Scanner;

public class Logistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cargo = Integer.parseInt(sc.nextLine());
        int kilosBus = 0;
        int kilosTruck = 0;
        int kilosTrain = 0;

        for (int i = 1; i <= cargo; i++) {

            int kilos = Integer.parseInt(sc.nextLine());

            if (kilos <= 3) {
                kilosBus +=kilos;
            } else if (kilos >= 4 && kilos <= 11) {
                kilosTruck +=kilos;
            } else {
                kilosTrain += kilos;
            }

        }

        //            •	До 3 тона – микробус (200 лева на тон)
        //•	От 4 до 11 тона – камион (175 лева на тон)
        //•	12 и повече тона – влак (120 лева на тон)
        int totalPrice = (kilosBus * 200) + (kilosTruck * 175) + (kilosTrain * 120);
        int totalKilos = kilosBus + kilosTruck + kilosTrain;
        System.out.printf("%.2f%n",(1.0 * totalPrice/totalKilos));
        System.out.printf("%.2f%%%n",(100.0 * kilosBus/totalKilos));
        System.out.printf("%.2f%%%n",(100.0 * kilosTruck/totalKilos));
        System.out.printf("%.2f%%%n",(100.0 * kilosTrain/totalKilos));

    }
}
