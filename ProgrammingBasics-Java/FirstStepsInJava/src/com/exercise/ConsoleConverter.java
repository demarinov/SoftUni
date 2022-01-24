package com.exercise;

import java.util.Scanner;

public class ConsoleConverter {

    public static void main(String[] args) {
        // 1 USD = 1.79549 BGN.

        Scanner scan = new Scanner(System.in);

        double dollar = Double.parseDouble(scan.nextLine());

        System.out.println((dollar * 1.79549d));

    }
}
