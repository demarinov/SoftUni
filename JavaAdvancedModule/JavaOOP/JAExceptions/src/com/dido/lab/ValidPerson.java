package com.dido.lab;

import java.util.Scanner;

public class ValidPerson {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Person jerry = new Person("Jerry", "Burtow", 19);
            Person noName = new Person(" ", "Burtow", 19);
            Person noLastName = new Person("Jerry", null, 19);
            Person negAge = new Person("Jerry", "Burtow", -1);

        } catch (IllegalArgumentException e) {
            System.out.println("Exception thrown: "+e.getLocalizedMessage());
        }


    }

    static class Person {

        private String firstName;
        private String lastName;
        private Integer age;

        public Person(String firstName, String lastName, Integer age) {
            setFirstName(firstName);
            setLastName(lastName);
            setAge(age);
        }

        public String getFirstName() {
            return firstName;
        }

        private void setFirstName(String firstName) {
            if (firstName == null || firstName.isBlank()) {
                throw new IllegalArgumentException("The first name cannot be null or empty");
            }
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        private void setLastName(String lastName) {

            if (lastName == null || lastName.isBlank()) {
                throw new IllegalArgumentException("The last name cannot be null or empty");
            }
            this.lastName = lastName;
        }

        public Integer getAge() {


            return age;
        }

        private void setAge(Integer age) {
            if (age < 0 || age > 120) {
                throw new IllegalArgumentException("Age should be in the range [0 ... 120]");
            }
            this.age = age;
        }
    }
}
