package com.dido.more;

import java.util.Scanner;

public class BikeRace {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int juniors = Integer.parseInt(sc.nextLine());
        int seniors = Integer.parseInt(sc.nextLine());
        String category = sc.nextLine();
        double money = 0.0d;
        double juniorMoney = 0.0d;
        double seniorMoney = 0.0d;

//        Група	trail	cross-country	downhill	road
//        juniors	5.50	8	12.25	20
//        seniors	7	9.50	13.75	21.50


        switch (category) {

            case "trail":
                juniorMoney = 5.50 * juniors;
                seniorMoney = 7.0 * seniors;
                break;
            case "cross-country":
                juniorMoney = 8.0 * juniors;
                seniorMoney = 9.50 * seniors;

                if (juniors + seniors >= 50) {
                    juniorMoney -= (0.25 * juniorMoney);
                    seniorMoney -= (0.25 * seniorMoney);
                }

                break;
            case "downhill":
                juniorMoney = 12.25 * juniors;
                seniorMoney = 13.75 * seniors;
                break;

            case "road":
                juniorMoney = 20.00 * juniors;
                seniorMoney = 21.50 * seniors;
                break;
            default:
                break;
        }


        money = juniorMoney + seniorMoney;
        money -= (0.05 * money);

        System.out.printf("%.2f",money);
    }
}
