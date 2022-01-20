package com.dido.more;

import java.util.Scanner;

public class TransportPrice {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int kilometers = Integer.parseInt(sc.nextLine());
        String day = sc.nextLine();

        double taxiPrice = 0.7d;
        double busPrice = 0.0d;
        double trainPrice = 0.0d;

//•	Такси. Начална такса: 0.70 лв. Дневна тарифа: 0.79 лв. / км. Нощна тарифа: 0.90 лв. / км.
//•	Автобус. Дневна / нощна тарифа: 0.09 лв. / км. Може да се използва за разстояния минимум 20 км.
//•	Влак. Дневна / нощна тарифа: 0.06 лв. / км. Може да се използва за разстояния минимум 100 км.



        if (day.equals("day")) {
            taxiPrice += 0.79 * kilometers;
            busPrice += (0.09 * kilometers);
            trainPrice += (0.06 * kilometers);
        } else if (day.equals("night")) {
            taxiPrice += 0.90 * kilometers;
            busPrice += (0.09 * kilometers);
            trainPrice += (0.06 * kilometers);

        }

        if (kilometers < 20) {
            busPrice *= 0;
        }

        if (kilometers < 100) {
            trainPrice *= 0;
        }

        double minPrice = taxiPrice;

        if (minPrice > busPrice && busPrice > 0) {
            minPrice = busPrice;
        }

        if (minPrice > trainPrice && trainPrice > 0) {
            minPrice = trainPrice;
        }

        System.out.printf("%.2f",minPrice);
    }
}
