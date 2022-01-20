package com.dido.more;

import java.util.Scanner;

public class Bills {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int months = Integer.parseInt(sc.nextLine());
        double currentTotal = 0.0d;
        double waterTotal = 0.0d;
        double internetTotal = 0.0d;
        double othersTotal = 0.0d;


        for (int i = 1; i <= months; i++) {

            double current = Double.parseDouble(sc.nextLine());
            currentTotal += current;
            waterTotal += 20.0d;
            internetTotal += 15.0d;
            double totalCosts3 = current + 20 + 15;
            othersTotal += (totalCosts3 + (0.20 * totalCosts3));

        }

        System.out.printf("Electricity: %.2f lv %n",(currentTotal));
        System.out.printf("Water: %.2f lv %n",(waterTotal));
        System.out.printf("Internet: %.2f lv %n",(internetTotal));
        System.out.printf("Other: %.2f lv %n",(othersTotal));
        double costsTotal = currentTotal + waterTotal + internetTotal + othersTotal;
        System.out.printf("Average: %.2f lv %n",(costsTotal / months));
    }
}
