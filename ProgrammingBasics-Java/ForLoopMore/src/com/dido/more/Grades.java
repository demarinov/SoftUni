package com.dido.more;

import java.util.Scanner;

public class Grades {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int students = Integer.parseInt(sc.nextLine());
        int topStudents = 0;
        int averageStudents = 0;
        int poorStudents = 0;
        int failedStudents = 0;
        double gradesSum = 0;

        for (int i = 1; i <= students; i++) {

            double grade = Double.parseDouble(sc.nextLine());

            gradesSum += grade;

            if (grade >= 5) {
                topStudents++;
            } else if (grade >= 4 && grade <= 4.99) {
                averageStudents++;
            } else if (grade >= 3 && grade <= 3.99) {
                poorStudents++;
            } else if (grade >= 2 && grade <= 2.99) {
                failedStudents++;
            }

        }

        System.out.printf("Top students: %.2f%%%n",(100.0 * topStudents / students));
        System.out.printf("Between 4.00 and 4.99: %.2f%%%n",(100.0 * averageStudents / students));
        System.out.printf("Between 3.00 and 3.99: %.2f%%%n",(100.0 * poorStudents / students));
        System.out.printf("Fail: %.2f%%%n",(100.0 * failedStudents / students));
        System.out.printf("Average: %.2f",(gradesSum / students));

    }
}
