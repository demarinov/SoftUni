package com.dido.lab;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaryIncrease {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] personData = sc.nextLine().split("\\s");
            Person person = new Person(personData[0],
                    personData[1], Integer.parseInt(personData[2]), Double.parseDouble(personData[3]));

            people.add(person);
        }

        double bonus = Double.parseDouble(sc.nextLine());

        for (int i = 0; i < people.size(); i++) {

            people.get(i).increaseSalary(bonus);
            System.out.println(people.get(i));
        }

    }

    static class Person {
        private String firstName;
        private String lastName;
        private Integer age;
        private double salary;

        public Person(String name, String lastName ,Integer age, double salary) {
            this.firstName = name;
            this.lastName = lastName;
            this.age = age;
            this.salary = salary;
        }

        public double salary() {
            return salary;
        }

        public void increaseSalary(double bonus) {

            if (this.age < 30) {
                bonus /= 2;
            }

            salary += (salary * bonus/100);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            DecimalFormat formatter = new DecimalFormat("0.0##");
            return String.format("%s %s gets %s leva",getFirstName(), getLastName()
                    , formatter.format(salary()));
        }
    }
}
