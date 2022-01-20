package com.dido.more;

import java.util.Map;
import java.util.Scanner;

public class Pets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int daysGone = Integer.parseInt(sc.nextLine());
        int foodLeftKilos = Integer.parseInt(sc.nextLine());
        double dogFoodPerDay = Double.parseDouble(sc.nextLine());
        double catFoodPerDay = Double.parseDouble(sc.nextLine());
        double turtleFoodPerDay = Double.parseDouble(sc.nextLine());

        double totalFoodKilos = daysGone * (dogFoodPerDay + catFoodPerDay + (turtleFoodPerDay/1000));

        if (foodLeftKilos >= totalFoodKilos) {
            System.out.printf("%d kilos of food left.",(int)Math.floor(foodLeftKilos - totalFoodKilos));
        } else {
            System.out.printf("%d more kilos of food are needed.", (int)Math.ceil(totalFoodKilos - foodLeftKilos));
        }

    }
}
