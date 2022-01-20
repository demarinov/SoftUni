package com.company.exercise;

import java.util.Scanner;

public class TrainTheTrainers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        double sumGradeTotal = 0.0d;
        int presentationCounter = 0;


        String input = sc.nextLine();

        while(!input.equals("Finish")) {

            String presentation = input;
            double sumGradePerPresentation = 0.0d;

            for (int i = 0; i < n; i++) {

                double grade = Double.parseDouble(sc.nextLine());

                sumGradePerPresentation +=grade;
            }

            double avGrade = sumGradePerPresentation/n;
            sumGradeTotal += avGrade;
            System.out.printf("%s - %.2f.%n"
                    ,presentation,avGrade);
            presentationCounter++;

            input = sc.nextLine();
        }

        System.out.printf("Student's final assessment is " +
                "%.2f" +
                ". ",(sumGradeTotal/presentationCounter));
    }
}
