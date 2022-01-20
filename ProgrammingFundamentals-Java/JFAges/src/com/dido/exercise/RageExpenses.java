package com.dido.exercise;

import java.util.Scanner;

public class RageExpenses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        •	On the first input line - lost games count – integer in the range [0, 1000].
//•	On the second line – headset price - floating point number in range [0, 1000].
//•	On the third line – mouse price - floating point number in range [0, 1000].
//•	On the fourth line – keyboard price - floating point number in range [0, 1000].
//•	On the fifth line – display price - floating point number in range [0, 1000].

        int lostGames = Integer.parseInt(sc.nextLine());
        double priceH = Double.parseDouble(sc.nextLine());
        double priceM = Double.parseDouble(sc.nextLine());
        double priceK = Double.parseDouble(sc.nextLine());
        double priceD = Double.parseDouble(sc.nextLine());

        int countH = 0;
        int countM = 0;
        int countK = 0;
        int countD = 0;

        for (int i = 1; i <= lostGames; i++) {

            boolean hTrashed = false;
            if (i % 2 == 0) {
                countH++;
                hTrashed  = true;
            }

            if (i % 3 == 0) {
                countM++;

                if (hTrashed) {
                    countK++;

                    if (countK % 2 == 0) {
                        countD++;
                    }
                }
            }


        }


        double expenses = (priceH * countH) + (priceM * countM) + (priceK * countK)
                + (priceD * countD);

        System.out.printf("Rage expenses: %.2f lv.",expenses);
    }
}
