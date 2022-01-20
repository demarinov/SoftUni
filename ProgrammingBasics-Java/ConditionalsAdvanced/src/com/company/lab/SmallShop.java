package com.company.lab;

import java.util.Scanner;

public class SmallShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String product = sc.nextLine();
        String city = sc.nextLine();
        double quantity = Double.parseDouble(sc.nextLine());

        if (city.equals("Sofia")) {
                //0.50,0.80,1.20,1.45,1.60
            if (product.equals("coffee")) {
                System.out.println((quantity * 0.5));
            } else if (product.equals("water")) {
                System.out.println((quantity * 0.8));
            } else if (product.equals("beer")) {
                System.out.println((quantity * 1.20));
            } else if (product.equals("sweets")) {
                System.out.println((quantity * 1.45));
            } else if (product.equals("peanuts")) {
                System.out.println((quantity * 1.60));
            }

        } else if (city.equals("Varna")) {
            // 0.45,0.70,1.10,1.35,1.55

            if (product.equals("coffee")) {
                System.out.println((quantity * 0.45));
            } else if (product.equals("water")) {
                System.out.println((quantity * 0.70));
            } else if (product.equals("beer")) {
                System.out.println((quantity * 1.1));
            } else if (product.equals("sweets")) {
                System.out.println((quantity * 1.35));
            } else if (product.equals("peanuts")) {
                System.out.println((quantity * 1.55));
            }
        } else if (city.equals("Plovdiv")) {
            // 0.40,0.70,1.15,1.30,1.50
            if (product.equals("coffee")) {
                System.out.println((quantity * 0.4));
            } else if (product.equals("water")) {
                System.out.println((quantity * 0.7));
            } else if (product.equals("beer")) {
                System.out.println((quantity * 1.15));
            } else if (product.equals("sweets")) {
                System.out.println((quantity * 1.30));
            } else if (product.equals("peanuts")) {
                System.out.println((quantity * 1.50));
            }
        }
    }
}
