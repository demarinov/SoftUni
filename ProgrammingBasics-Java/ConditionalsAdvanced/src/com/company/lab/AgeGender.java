package com.company.lab;

import java.util.Scanner;

public class AgeGender {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double age = Double.parseDouble(sc.nextLine());
        String gender = sc.nextLine();

        if (gender.equals("m")) {

            if (age >= 16d) {
                System.out.println("Mr.");
            } else {
                System.out.println("Master");
            }

        } else {
            if (age >= 16d) {
                System.out.println("Ms.");
            } else {
                System.out.println("Miss");
            }

        }

    }
}
