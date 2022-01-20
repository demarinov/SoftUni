package com.dido.exams2;

import java.util.Scanner;

public class SoftUniReception {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int studentsPartOne = Integer.parseInt(sc.nextLine());
        int studentsPartTwo = Integer.parseInt(sc.nextLine());
        int studentsPartThree = Integer.parseInt(sc.nextLine());
        int totalStudents = Integer.parseInt(sc.nextLine());

        int totalStudentsPerHour = studentsPartOne + studentsPartThree + studentsPartTwo;
        int amountOfStudentsHandled = 0;

        int countHours = 0;
        while(amountOfStudentsHandled < totalStudents) {
            // count hours
            countHours++;

            if (countHours % 4 == 0) {
                // break time
                continue;
            }
            amountOfStudentsHandled += totalStudentsPerHour;

        }

        System.out.println("Time needed: "+countHours+"h.");
    }
}
