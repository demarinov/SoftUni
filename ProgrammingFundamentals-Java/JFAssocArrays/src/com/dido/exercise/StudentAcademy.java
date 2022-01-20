package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class StudentAcademy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, List<Double>> students = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String name  = sc.nextLine();
            Double grade = Double.parseDouble(sc.nextLine());

            if (students.containsKey(name)) {
                List<Double> grades = students.get(name);

                grades.add(grade);
            } else {
                List<Double> grades = new ArrayList<>();
                grades.add(grade);
                students.put(name, grades);
            }
        }

        students.entrySet()
                .stream()
                .map(s -> Map.entry(s.getKey(),s.getValue().stream().mapToDouble(Double::doubleValue).average().getAsDouble()))
                .filter(s -> s.getValue() >= 4.5d)
                .sorted((s1,s2) -> s2.getValue().compareTo(s1.getValue()))
                .map(s -> String.format("%s -> %.2f",s.getKey(),s.getValue()))
                .forEach(System.out::println);

    }
}
