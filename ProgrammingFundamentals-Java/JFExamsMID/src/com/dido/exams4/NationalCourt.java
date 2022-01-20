package com.dido.exams4;

import java.util.Scanner;

public class NationalCourt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int empOneRate = Integer.parseInt(sc.nextLine());
        int empTwoRate = Integer.parseInt(sc.nextLine());
        int empThreeRate = Integer.parseInt(sc.nextLine());

        int totalPeople = Integer.parseInt(sc.nextLine());

        int totalHandledPeople = 0;

        int count = 0;
        while(totalHandledPeople < totalPeople) {

            count++;
            if (count % 4 == 0) {
                // break time
                continue;
            }

            totalHandledPeople += (empOneRate + empTwoRate + empThreeRate);

        }

        System.out.printf("Time needed: %dh.",count);
    }
}
