package com.dido.exams5;

import java.util.Scanner;

public class BonusScoringSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int students = Integer.parseInt(sc.nextLine());
        int lectures = Integer.parseInt(sc.nextLine());
        int initialBonus = Integer.parseInt(sc.nextLine());

//        {total bonus} = {student attendances} / {course lectures} * (5 + {additional bonus})

        double maxBonus = 0;
        int maxAttendance = 0;


        for (int i = 0; i < students; i++) {

            int attendance = Integer.parseInt(sc.nextLine());

            double totalBonus = 0;

            if (lectures > 0) {
                totalBonus = (1.0 * attendance / lectures) * (5 + initialBonus);
            }

            if (maxBonus < totalBonus) {
                maxBonus = totalBonus;
                maxAttendance = attendance;
            }
        }

        // print the max bonus
        System.out.printf("Max Bonus: %.0f.%n", Math.ceil(maxBonus));
        System.out.printf("The student has attended %d lectures.%n", maxAttendance);


    }
}
