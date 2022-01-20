package com.dido.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Students {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<Student> studentList = new ArrayList<>();

        while(!input.equalsIgnoreCase("end")) {

            String[] studentData = input.split("\\s+");

            Student student = new Student(studentData[0],studentData[1]
                    , Integer.parseInt(studentData[2]), studentData[3]);

            studentList.add(student);

            input =sc.nextLine();
        }

        input = sc.nextLine();

        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            if (student.getHometown().equalsIgnoreCase(input)) {
                System.out.println(student);
            }
        }

    }
}

class Student {

    private String firstName;
    private String lastName;
    private Integer age;
    private String hometown;

    public Student(String firstName, String lastName, Integer age, String hometown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.hometown = hometown;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    @Override
    public String toString() {
        return firstName+" "+lastName+" is "+age+" years old";
    }
}
