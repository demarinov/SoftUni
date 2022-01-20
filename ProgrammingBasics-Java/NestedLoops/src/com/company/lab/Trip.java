package com.company.lab;

import java.util.Scanner;

public class Trip {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        String input  = sc.nextLine();
        double moneyNeeded = 0.0;
        double moneySaved = 0.0;
        
        while(!input.equals("End")) {

            moneyNeeded = Double.parseDouble(sc.nextLine());

            while (moneySaved < moneyNeeded) {

                double moneyIn = Double.parseDouble(sc.nextLine());
                moneySaved += moneyIn;
            }

            System.out.println("Going to "+input+"!");

            input = sc.nextLine();
            moneySaved = 0.0;
        }
    }
}
