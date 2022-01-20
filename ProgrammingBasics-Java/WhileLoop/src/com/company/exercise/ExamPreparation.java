package com.company.exercise;

import java.util.Scanner;

public class ExamPreparation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numBadGrades = Integer.parseInt(sc.nextLine());
        int countBadGrades = 0;
        int problemsSolved = 0;
        boolean isDone = false;
        String lastProblemName = "";
        int gradesSum = 0;
        double avGrade = 0.0;

        while (countBadGrades < numBadGrades) {

            String problemName = sc.nextLine();
            if (problemName.equals("Enough")) {

                isDone = true;
                break;
            }


            int grade = Integer.parseInt(sc.nextLine());

            if (grade <= 4.0) {
                countBadGrades++;
            }

            gradesSum += grade;
            problemsSolved++;
            lastProblemName = problemName;
        }



        if (isDone) {
            avGrade = 1.0 * gradesSum / problemsSolved;
            System.out.printf("Average score: %.2f\n",
                    avGrade);
            System.out.println("Number of problems: " +
                    problemsSolved);
            System.out.println("Last problem: " +
                    lastProblemName);
        } else {
            System.out.println("You need a break, " +
                    countBadGrades + " poor grades.");
        }


    }
}
