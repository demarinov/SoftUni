package com.company.lab;

import java.util.Scanner;

public class TradeCommissions {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String town = sc.nextLine();
        double volSales = Double.parseDouble(sc.nextLine());

        if (town.equals("Sofia")) {
            // 5%	7%	8%	12%
            if (volSales >=0 && volSales <= 500) {
                System.out.printf("%.2f",(volSales * 0.05));

            } else if (volSales >500 && volSales <= 1000) {
                System.out.printf("%.2f",(volSales * 0.07));
            } else if (volSales > 1000 && volSales <= 10000) {
                System.out.printf("%.2f",(volSales * 0.08));
            } else if (volSales > 10000) {
                System.out.printf("%.2f",(volSales * 0.12));
            } else {
                System.out.println("error");
            }

        } else if (town.equals("Varna")) {
            // 4.5%	7.5%	10%	13%

            if (volSales >=0 && volSales <= 500) {
                System.out.printf("%.2f",(volSales * 0.045));
            } else if (volSales >500 && volSales <= 1000) {
                System.out.printf("%.2f",(volSales * 0.075));
            } else if (volSales > 1000 && volSales <= 10000) {
                System.out.printf("%.2f",(volSales * 0.10));
            } else if (volSales > 10000) {
                System.out.printf("%.2f",(volSales * 0.13));
            } else {
                System.out.println("error");
            }

        } else if (town.equals("Plovdiv")) {

            // 5.5%	8%	12%	14.5%

            if (volSales >=0 && volSales <= 500) {
                System.out.printf("%.2f",(volSales * 0.055));
            } else if (volSales >500 && volSales <= 1000) {
                System.out.printf("%.2f",(volSales * 0.08));
            } else if (volSales > 1000 && volSales <= 10000) {
                System.out.printf("%.2f",(volSales * 0.12));
            } else if (volSales > 10000) {
                System.out.printf("%.2f",(volSales * 0.145));
            } else {
                System.out.println("error");
            }

        } else {
            System.out.println("error");
        }
    }
}
