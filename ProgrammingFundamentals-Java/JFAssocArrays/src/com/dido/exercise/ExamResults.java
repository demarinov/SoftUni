package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ExamResults {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, Integer> userMaxPoints = new LinkedHashMap<>();
        Map<String, Integer> submissions = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("exam finished")) {

            String[] examData = input.split("-");

            if (examData[1].equalsIgnoreCase("banned")) {
                String user = examData[0];

                userMaxPoints.remove(user);
                input = sc.nextLine();
                continue;
            }


            String user = examData[0];
            String language = examData[1];
            Integer points = Integer.parseInt(examData[2]);

            if (userMaxPoints.containsKey(user)) {
                Integer currentPoints = userMaxPoints.get(user);

                if (currentPoints < points) {
                    userMaxPoints.put(user, points);
                }
            }

            userMaxPoints.putIfAbsent(user, points);

            submissions.putIfAbsent(language, 0);
            submissions.put(language, submissions.get(language)+1);

            input = sc.nextLine();
        }

        System.out.println("Results:");
        userMaxPoints.entrySet().stream()
                .sorted((u1,u2) -> {
                    int result = u2.getValue().compareTo(u1.getValue());
                    return result == 0 ? u1.getKey().compareTo(u2.getKey()) : result;
                }).map(u -> String.format("%s | %d",u.getKey(), u.getValue()))
        .forEach(System.out::println);
        System.out.println("Submissions:");
        submissions.entrySet().stream()
                .sorted((s1, s2) -> {
                    int result = s2.getValue().compareTo(s1.getValue());

                    return result == 0 ?s1.getKey().compareTo(s2.getKey()) : result;
                }).map(s -> String.format("%s - %d",s.getKey(), s.getValue()))
                .forEach(System.out::println);
    }
}
