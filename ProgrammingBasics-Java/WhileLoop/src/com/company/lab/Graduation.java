package com.company.lab;

import java.util.Scanner;

public class Graduation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int i = 1;
        int timesFailed = 0;
        double sumGrade = 0;
        String name = sc.nextLine();

        while(i <= 12) {

            if (timesFailed > 1) {
                break;
            }

            double grade = Double.parseDouble(sc.nextLine());


            if (grade < 4) {
                timesFailed++;
                continue;
            }

            sumGrade += grade;
            i++;

        }

        if (timesFailed > 1) {
            System.out.println(name+" has been excluded at " +
                    i +
                    " grade");
        } else {

            double avGrade = 1.0 * sumGrade / 12;
            System.out.printf(name+" graduated. Average grade: %.2f",avGrade);
        }

    }
}
