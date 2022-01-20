package com.dido.exams;

import java.util.Scanner;

public class SFReception {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int studentsForEmployeeOne = Integer.parseInt(sc.nextLine());
        int studentsForEmployeeTwo = Integer.parseInt(sc.nextLine());
        int studentsForEmployeeThree = Integer.parseInt(sc.nextLine());
        int studentsToServe = Integer.parseInt(sc.nextLine());

        int totalServedStudentsPerHour = studentsForEmployeeOne + studentsForEmployeeTwo + studentsForEmployeeThree;
        int countHours = 0;

        while(studentsToServe > 0) {

            countHours++;

            if (countHours % 4 == 0) {
                continue;
            }

            studentsToServe -= totalServedStudentsPerHour;

        }

        System.out.printf("Time needed: %dh",countHours);
    }
}
