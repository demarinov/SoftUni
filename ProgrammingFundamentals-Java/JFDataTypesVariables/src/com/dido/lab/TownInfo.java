package com.dido.lab;

import java.util.Scanner;

public class TownInfo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String town = sc.nextLine();
        long population = Long.parseLong(sc.nextLine());
        double area = Double.parseDouble(sc.nextLine());

        System.out.printf("Town %s has population of %d and area %.0f square km.",town,population,area);
    }
}
