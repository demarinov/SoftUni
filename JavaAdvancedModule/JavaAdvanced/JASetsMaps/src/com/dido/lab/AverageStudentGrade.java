package com.dido.lab;

import java.util.*;

public class AverageStudentGrade {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program, which reads the name of a student and their grades and adds them to
//        the student record, then prints grades along with their
//        average grade – ordered the output by the names of the students.

//        On the first line N – the number of students, then on the next N lines student name with grade.

        int n = Integer.parseInt(sc.nextLine());

        Map<String, List<Double>> studentGrades = new TreeMap<>();
        for (int i = 0; i < n; i++) {

            String[] inputData = sc.nextLine().split("\\s");
            String name = inputData[0];
            Double grade = Double.parseDouble(inputData[1]);

            studentGrades.putIfAbsent(name, new ArrayList<>());
            List<Double> grades = studentGrades.get(name);
            grades.add(grade);
            studentGrades.put(name, grades);
        }

        studentGrades.entrySet().stream()
                .map(e ->
                {
                    // potential double rounding with avg func and format specifier later ...
//                    Double avg = e.getValue().stream().mapToDouble(d -> d).average().getAsDouble();
                    double avg = 0.0d;
                    double sum = 0.0d;
                    for (int i = 0; i < e.getValue().size(); i++) {
                        sum += e.getValue().get(i);
                    }

                    avg = sum / e.getValue().size();
                    String values = e.getValue().stream().map(d -> String.format("%.2f ",d))
                            .reduce("",String::concat);
                    return String
                            .format("%s -> %s(avg: %.2f)", e.getKey(),
                                    values, avg);
                })
                .forEach(System.out::println);
    }
}
