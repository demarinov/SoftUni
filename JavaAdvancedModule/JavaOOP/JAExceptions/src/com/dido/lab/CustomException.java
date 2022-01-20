package com.dido.lab;

import java.util.Scanner;

public class CustomException {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            Student student = new Student("4havdar","buksy@mail.bg");
        } catch (InvalidPersonNameException | IllegalArgumentException  e) {
            System.out.println("Exception thrown: "+e.getLocalizedMessage());
        }

    }

    static class Student {

        private String name;
        private String email;

        public Student(String name, String email) throws InvalidPersonNameException {
            setName(name);
            setEmail(email);
        }

        public String getName() {
            return name;
        }

        private void setName(String name) throws InvalidPersonNameException {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("The name cannot be null or empty");
            }


            if (!onlyLetters(name)) {
                throw new InvalidPersonNameException("The name cannot contain special characters or numbers");
            }
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        private void setEmail(String email) {
            this.email = email;
        }
    }

    public static boolean onlyLetters(String str) {

        for (int i = 0; i < str.length(); i++) {

            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    static class InvalidPersonNameException extends Exception {

        public InvalidPersonNameException(String message) {
            super(message);
        }
    }
}
