package com.lab;

import java.util.Scanner;

public class GreenYard {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double yards = Double.parseDouble(scan.nextLine());
        double price = yards * 7.61d;
        double discount = 0.18d * price;

        System.out.println("The final price is: "+(price - discount)+" lv.");
        System.out.println("The discount is: "+discount+" lv.");

    }
}
