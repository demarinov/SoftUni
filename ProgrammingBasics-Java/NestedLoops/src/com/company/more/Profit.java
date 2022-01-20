package com.company.more;

import java.util.Scanner;

public class Profit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int oneLevs = Integer.parseInt(sc.nextLine());
        int twoLevs = Integer.parseInt(sc.nextLine());
        int fiveLevs = Integer.parseInt(sc.nextLine());
        int sum = Integer.parseInt(sc.nextLine());

        for (int i = 0; i <= oneLevs; i++) {
            for (int j = 0; j <= twoLevs; j++) {
                for (int k = 0; k <= fiveLevs; k++) {
                    int amount = (i * 1) + (j * 2) + (k * 5);

                    if (amount == sum) {
                        System.out.println(i +
                                " " +
                                "* 1 lv. + " +
                                j +
                                " " +
                                "* 2 lv. + " +
                                k +
                                " * 5 lv. = " +
                                sum +
                                " lv.");
                    }
                }
            }
        }
    }
}
