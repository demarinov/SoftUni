package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class Courses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, List<String>> courses = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("end")) {

            String[] courseData = input.split(" : ");
            String course =courseData[0];
            String student =courseData[1];

            registerStudent(courses, course, student);

            input = sc.nextLine();
        }

        printCourses(courses);
    }

    public static void printCourses(Map<String, List<String>> courses) {

        List<Map.Entry<String,List<String>>> sortedCourses = courses.entrySet().stream()
                .sorted((e1,e2) -> e2.getValue().size() - e1.getValue().size())
                .collect(Collectors.toList());
        for(Map.Entry<String, List<String>> course:sortedCourses) {

            String courseName = course.getKey();
            List<String> students = course.getValue().stream()
                    .sorted((s1,s2) -> s1.compareTo(s2)).collect(Collectors.toList());
            System.out.println(courseName+": "+students.size());

            students.stream().map(s -> String.format("-- %s",s)).forEach(System.out::println);
        }
    }

    public static void registerStudent(Map<String, List<String>> courses, String course, String student) {

        if (!courses.containsKey(course)) {
            List<String> students = new ArrayList<>();
            students.add(student);
            courses.put(course, students);
        } else {
            List<String> students = courses.get(course);

            // check if user already registered
            if (!students.contains(student)) {
                students.add(student);
            }
        }
    }
}
