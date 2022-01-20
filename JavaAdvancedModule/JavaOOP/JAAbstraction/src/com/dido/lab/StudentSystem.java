package com.dido.lab;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentSystem {

    enum Commands {
        CREATE("Create"),SHOW("Show"),EXIT("Exit");

        private String value;

        Commands(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();

        while (true) {
            String[] input = scanner.nextLine().split(" ");
            if (Commands.EXIT.getValue().equals(input[0])) {
                break;
            }
            studentSystem.ParseCommand(input);
        }

    }

    private static class Student {
        private String name;
        private int age;
        private double grade;

        public Student(String name, int age, double grade) {
            this.name = name;
            this.age = age;
            this.grade = grade;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return this.age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public double getGrade() {
            return this.grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }
    }

    private Map<String, Student> studentRepo;

    public StudentSystem() {
        this.studentRepo = new HashMap<>();
    }

    public Map<String, Student> getStudentRepo() {
        return this.studentRepo;
    }

    private void showStudent(String name){

        if (studentRepo.containsKey(name)) {
            var student = studentRepo.get(name);
            StringBuilder studentView = new StringBuilder();
                    studentView.append(String.format("%s is %s years old.",
                            student.getName(), student.getAge()));

            if (student.getGrade() >= 5.00) {
                studentView.append(" Excellent student.");
            } else if (student.getGrade() < 5.00 && student.getGrade() >= 3.50) {
                studentView.append(" Average student.");
            } else {
                studentView.append(" Very nice person.");
            }

            System.out.println(studentView);
        }
    }

    private void createStudent(String[] studentData) {
        var name = studentData[1];
        var age = Integer.parseInt(studentData[2]);
        var grade = Double.parseDouble(studentData[3]);

        if (!studentRepo.containsKey(name)) {
            var student = new Student(name, age, grade);
            studentRepo.put(name, student);
        }
    }

    public void ParseCommand(String[] commandData) {

        if (Commands.CREATE.getValue().equals(commandData[0])) {
            createStudent(commandData);
        } else if (Commands.SHOW.getValue().equals(commandData[0])) {
            showStudent(commandData[1]);
        }
    }


}
