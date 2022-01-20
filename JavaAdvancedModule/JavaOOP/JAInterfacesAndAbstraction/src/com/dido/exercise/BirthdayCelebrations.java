package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BirthdayCelebrations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String input  = sc.nextLine();

        List<Identifiable> robotList = new ArrayList<>();
        List<Birthable> birthableObjectList = new ArrayList<>();
        while(!"End".equals(input)) {

            String[] data = input.split("\\s");

            switch(data[0]) {

                case "Citizen":
                    // Citizen <name> <age> <id> <birthdate>
                    birthableObjectList.add(new Citizen(data[1], Integer.parseInt(data[2])
                            , data[3], data[4]));

                    break;
                case "Robot":
                    // Robot <model> <id>
                    robotList.add(new Robot(data[1], data[2]));
                    break;
                case "Pet":
                    // Pet <name> <birthdate>
                    birthableObjectList.add(new Pet(data[1], data[2]));
                    break;
            }

            input = sc.nextLine();
        }

        String year = sc.nextLine();

        birthableObjectList.stream().filter(bo-> bo.getBirthDate().endsWith(year))
                .map(bo -> bo.getBirthDate())
                .forEach(System.out::println);

    }

    interface Birthable{
        String getBirthDate();
    }

    interface Identifiable {

        String getId();
    }

    interface Person {

        String getName();
        int getAge();
    }

    static class Pet implements Birthable {

        private String name;
        private String birthDay;

        public Pet(String name, String birthDay) {
            setName(name);
            setBirthDay(birthDay);
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setBirthDay(String birthDay) {
            this.birthDay = birthDay;
        }

        @Override
        public String getBirthDate() {
            return this.birthDay;
        }
    }

    static class Robot implements Identifiable {

        private String id;
        private String model;

        public Robot(String id, String model) {
            setId(id);
            setModel(model);
        }

        private void setId(String id) {
            this.id = id;
        }

        private void setModel(String model) {
            this.model = model;
        }

        @Override
        public String getId() {
            return this.id;
        }
    }

    static class Citizen implements Person, Birthable, Identifiable {

        private String name;
        private int age;
        private String id;
        private String birthDate;

        public Citizen(String name, int age, String id, String birthDate) {
            setName(name);
            setAge(age);
            setId(id);
            setBirthDate(birthDate);
        }

        private void setId(String id) {
            this.id = id;
        }

        private void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setAge(int age) {
            this.age = age;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public int getAge() {
            return this.age;
        }


        @Override
        public String getBirthDate() {
            return this.birthDate;
        }

        @Override
        public String getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "Citizen{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id='" + id + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    '}';
        }
    }
}
