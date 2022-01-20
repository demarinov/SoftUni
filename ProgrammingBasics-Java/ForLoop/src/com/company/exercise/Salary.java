package com.company.exercise;

import java.util.Scanner;

public class Salary {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int salary = Integer.parseInt(sc.nextLine());
        int salaryLeft = salary;

        for (int i = 1; i <= n; i++) {

            String siteName = sc.nextLine();

            switch (siteName) {

                case "Facebook":
                    salaryLeft -= 150;
                    break;
                case "Instagram":
                    salaryLeft -= 100;
                    break;
                case "Reddit":
                    salaryLeft -= 50;
                    break;
                default:
                    break;
            }


        }

        if (salaryLeft <= 0) {
            System.out.println("You have lost your salary.");
        } else {
            System.out.println(salaryLeft);
        }

    }
}
