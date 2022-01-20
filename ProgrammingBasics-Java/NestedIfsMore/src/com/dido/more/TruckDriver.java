package com.dido.more;

import java.util.Scanner;

public class TruckDriver {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String season = sc.nextLine();
        double kmPerMonth = Double.parseDouble(sc.nextLine());
        double salary = 0.0d;

//        Пролет/Есен	Лято	Зима
//        км на месец <= 5000	0.75 лв./км	0.90 лв./км	1.05 лв./км
//        5000 < км на месец <= 10000	0.95 лв./км	1.10 лв./км	1.25 лв./км
//        10000 < км на месец <= 20000	1.45 лв./км – за който и да е сезон

        if (kmPerMonth <= 5000) {

            if (season.equals("Spring") || season.equals("Autumn")) {
                salary = 0.75 * 4 * kmPerMonth;
            } else if (season.equals("Summer")) {
                salary = 0.90 * 4 * kmPerMonth;
            } else if (season.equals("Winter")) {
                salary = 1.05 * 4 * kmPerMonth;
            }

        } else if (kmPerMonth > 5000 && kmPerMonth <= 10000) {
            if (season.equals("Spring") || season.equals("Autumn")) {
                salary = 0.95 * 4 * kmPerMonth;
            } else if (season.equals("Summer")) {
                salary = 1.10 * 4 * kmPerMonth;
            } else if (season.equals("Winter")) {
                salary = 1.25 * 4 * kmPerMonth;
            }
        } else if (kmPerMonth > 10000 && kmPerMonth <= 20000) {
            if (season.equals("Spring")
                    || season.equals("Autumn")
                    || season.equals("Summer")
                    || season.equals("Winter") ) {
                salary = 1.45 * 4 * kmPerMonth;
            }
        }

        salary -= (0.1 * salary);

        System.out.printf("%.2f",salary);
    }
}
