package com.company.exercise;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String projection = sc.nextLine();
        int rows = Integer.parseInt(sc.nextLine());
        int columns = Integer.parseInt(sc.nextLine());

        double totalIncome = 0.0d;

        // prices -> 12, 7.5 5

        switch(projection) {

            case "Premiere":
                totalIncome = rows * columns * 12.0d;
                break;
            case "Normal":
                totalIncome = rows * columns * 7.5d;
                break;

            case "Discount":
                totalIncome = rows * columns * 5.0d;
                break;

            default:
                break;
        }

        System.out.printf("%.2f leva",totalIncome);;
    }
}
