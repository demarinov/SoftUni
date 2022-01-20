package com.dido.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SoftUniCoursePlanning {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> schedule = new ArrayList(Arrays.asList(sc.nextLine().split(", ")));

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("course start")) {

            if (command.toLowerCase().contains("add")) {
                addLesson(schedule, command.split(":")[1]);
            } else if (command.toLowerCase().contains("insert")) {
                String title = command.split(":")[1];
                int index = Integer.parseInt(command.split(":")[2]);
                insertLessonTitle(schedule,title, index);
            } else if (command.toLowerCase().contains("remove")) {

                removeTitle(schedule, command.split(":")[1]);
            } else if (command.toLowerCase().contains("swap")) {

                swapTitles(schedule, command.split(":")[1], command.split(":")[2]);
            } else if (command.toLowerCase().contains("exercise")) {

                addExercise(schedule, command.split(":")[1]);
            }

            command = sc.nextLine();
        }

        printSchedule(schedule);
    }

    public static void printSchedule(List<String> schedule) {
        for (int i = 0; i < schedule.size(); i++) {

            System.out.printf("%d.%s%n",i+1,schedule.get(i));
        }
    }

    public static void addExercise(List<String> schedule, String title) {

        int titleIdx = schedule.indexOf(title);
        String exercise = title+"-Exercise";
        if (titleIdx != -1) {

            if (titleIdx < schedule.size()-1 && !schedule.get(titleIdx+1).contains(exercise)) {
                schedule.add(titleIdx+1,exercise);
            } else if (titleIdx == schedule.size()-1) {
                schedule.add(exercise);
            }

        } else {
            schedule.add(title);
            schedule.add(exercise);
        }
    }

    public static void swapTitles(List<String> schedule, String firstTitle, String secondTitle) {

        int firstIdx = schedule.indexOf(firstTitle);
        int secondIdx = schedule.indexOf(secondTitle);

        boolean swap = false;
        if (firstIdx != -1 && secondIdx != -1 && firstIdx != secondIdx) {
            swap = true;
        }

        if (swap) {

            String firstExercise = schedule.get(firstIdx)+"-Exercise";
            String secondExercise = schedule.get(secondIdx)+"-Exercise";

            String temp = schedule.get(firstIdx);
            schedule.set(firstIdx,schedule.get(secondIdx));
            schedule.set(secondIdx,temp);




            if (schedule.contains(firstExercise)) {
                // swap exercise also

                if (secondIdx+1 < schedule.size()-1) {
                    schedule.remove(firstExercise);
                    schedule.add(secondIdx+1,firstExercise);
                } else if (secondIdx + 1 == schedule.size()-1) {
                    schedule.add(firstExercise);
                }

            }

            if (schedule.contains(secondExercise)) {
                if (firstIdx+1 < schedule.size()-1) {
                    schedule.remove(secondExercise);
                    schedule.add(firstIdx+1,secondExercise);
                } else if (firstIdx + 1 == schedule.size()-1) {
                    schedule.add(secondExercise);
                }
            }
        }
    }

    public static void removeTitle(List<String> schedule, String title) {

        if (schedule.contains(title)) {
            int titleIdx = schedule.indexOf(title);
            schedule.remove(titleIdx);
            String exercise = title+"-Exercise";

            if (schedule.contains(exercise)) {
                schedule.remove(exercise);
            }
        }
    }

    public static void insertLessonTitle(List<String> schedule,String title, int index) {

        if (!schedule.contains(title)) {
            schedule.add(index, title);
        }
    }

    public static void addLesson(List<String> schedule, String lesson) {

        if (!schedule.contains(lesson)) {

            schedule.add(lesson);
        }
    }
}
