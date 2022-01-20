package com.company.exercise;

import java.util.Scanner;

public class ExamOnTime {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int examHour = Integer.parseInt(sc.nextLine());
        int examMin = Integer.parseInt(sc.nextLine());

        int arriveHour = Integer.parseInt(sc.nextLine());
        int arriveMin = Integer.parseInt(sc.nextLine());


        int totalExamMin = examHour * 60 + examMin ;
        int totalArriveMin = arriveHour * 60 + arriveMin;

        int minDiff = totalExamMin - totalArriveMin;
        int hourDiff = minDiff / 60 ;
        int remMinDiff = minDiff % 60 ;

        if (minDiff >= 0 && minDiff <= 30) {
            System.out.println("On time");

            if (minDiff >= 1) {

                if (hourDiff > 0) {
                    System.out.printf("%d:%02d hours before the start", hourDiff, remMinDiff);
                } else {
                    System.out.printf("%d minutes before the start", minDiff);
                }
            }

        } else if (minDiff < 0) {
            System.out.println("Late");

            if (Math.abs(minDiff) >= 1) {
                if (Math.abs(hourDiff) > 0) {
                    System.out.printf("%d:%02d hours after the start", Math.abs(hourDiff), Math.abs(remMinDiff));
                } else {
                    System.out.printf("%d minutes after the start", Math.abs(minDiff));
                }
            }

        } else if (minDiff > 30) {
            System.out.println("Early");

            if (minDiff >= 1) {

                if (hourDiff > 0) {
                    System.out.printf("%d:%02d hours before the start", hourDiff, remMinDiff);
                } else {
                    System.out.printf("%d minutes before the start", minDiff);
                }
            }
        }


    }
}
