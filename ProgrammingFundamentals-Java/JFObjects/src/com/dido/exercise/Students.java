package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Students {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //n count of students and orders them by grade (in descending).

        int n = Integer.parseInt(sc.nextLine());
        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] studentData = sc.nextLine().split("\\s+");
            Student student = new Student(studentData[0], studentData[1]
                    , Double.parseDouble(studentData[2]));
            studentList.add(student);
        }

        List<Student> sortedStudentList = studentList.stream()
                .sorted((o1,o2) -> o2.grade.compareTo(o1.grade)).collect(Collectors.toList());

        for (int i = 0; i < sortedStudentList.size(); i++) {

            System.out.println(sortedStudentList.get(i).toString());
        }
    }
}

class Student {

    String firstName;
    String lastName;
    Double grade;

    public Student(String firstName, String lastName, double grade) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return firstName + " "+ lastName + ": "+String.format("%.2f",grade);
    }
}
