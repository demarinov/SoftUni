package com.exercise;

import java.util.Scanner;

public class DepositCalculator {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double deposit = Double.parseDouble(scan.nextLine());
        int months = Integer.parseInt(scan.nextLine());
        double annualPercentage = Double.parseDouble(scan.nextLine());

        double interest = deposit + months * ((deposit * annualPercentage/100d)/12);

        System.out.println(interest);
    }
}
