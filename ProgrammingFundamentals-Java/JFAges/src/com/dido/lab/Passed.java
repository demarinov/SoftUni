package com.dido.lab;

import java.util.Scanner;

public class Passed {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double grade = Double.parseDouble(sc.nextLine());

        if (grade >= 3.0) {
            System.out.printf("Passed!");
        }
    }
}
