package com.company.exercise;

import java.util.Scanner;

public class Scholarship {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double salary = Double.parseDouble(sc.nextLine());
        double avGrade = Double.parseDouble(sc.nextLine());
        double minSalary = Double.parseDouble(sc.nextLine());

        double socialScholarship = 0.0d;
        double excellenceScholarship = 0.0d;

        int counterScholarship = 0;

        if (avGrade <= 4.5) {
            counterScholarship++;
        } else if (salary < minSalary) {
            socialScholarship = minSalary * 0.35d;
        }

        if (salary >= minSalary) {
            counterScholarship++;
        }

        if (avGrade >= 5.5) {
            excellenceScholarship = avGrade * 25;
        } else {
            counterScholarship++;
        }

        if (counterScholarship > 1) {
            System.out.println("You cannot get a scholarship!");
        }
        else if (socialScholarship > excellenceScholarship) {
            System.out.printf("%s%.0f%s","You get a Social scholarship ",
                    Math.floor(socialScholarship),
                    " BGN");
        } else {
            System.out.printf("%s%.0f%s","You get a scholarship for excellent results ",
                    Math.floor(excellenceScholarship),
                    " BGN");
        }
    }
}
