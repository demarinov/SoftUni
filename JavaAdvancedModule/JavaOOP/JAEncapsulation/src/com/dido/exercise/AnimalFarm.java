package com.dido.exercise;

import java.util.Scanner;

public class AnimalFarm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String name = sc.nextLine();
        Integer age = Integer.parseInt(sc.nextLine());

        try {
            Chicken chicken = new Chicken(name, age);

            System.out.println(chicken);
        }  catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    static class Chicken{

        private String name;
        private int age;

        public Chicken(String name, int age) {
            setName(name);
            setAge(age);
        }

        public String getName() {
            return name;
        }

        public double productPerDay() {
            return calculateProductPerDay();
        }

        private double calculateProductPerDay() {
            if (age <= 5) {
                return 2.0d;
            } else if (age <= 11) {
                return 1.0d;
            } else {
                return 0.75d;
            }
        }

        private void setName(String name) {

            if (name == null || "".equals(name) || "    ".equals(name)) {
                throw new IllegalArgumentException("Name cannot be empty.");
            }
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        private void setAge(int age) {

            if (age < 0 || age > 15) {
                throw new IllegalArgumentException("Age should be between 0 and 15.");
            }
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("Chicken %s (age %d) can produce %.2f eggs per day.",
                    getName(),getAge(), productPerDay());
        }
    }
}
