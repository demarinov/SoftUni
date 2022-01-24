package com.lab;

import java.util.Scanner;

public class ZooShop {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int dogNum = Integer.parseInt(scan.nextLine());
        int animalsNum = Integer.parseInt(scan.nextLine());

        System.out.println((dogNum * 2.50d + animalsNum * 4)+" lv.");
    }
}
