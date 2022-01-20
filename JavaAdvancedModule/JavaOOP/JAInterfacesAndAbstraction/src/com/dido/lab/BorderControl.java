package com.dido.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BorderControl {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Identifiable> borderObjectList = new ArrayList<>();
        while(!"End".equals(input)) {

            //"<name> <age> <id>" for citizens and "<model> <id>" for robots.
            String[] data = input.split("\\s");

            if (data.length == 3) {
                // citizen
                Citizen citizen = new Citizen(data[0], Integer.parseInt(data[1]),data[2]);
                borderObjectList.add(citizen);
            } else {
                // robot
                Robot robot = new Robot(data[1], data[0]);
                borderObjectList.add(robot);
            }

            input = sc.nextLine();
        }

        String fakeIdLastDigits = sc.nextLine();

        borderObjectList.stream().filter(bo -> bo.getId().endsWith(fakeIdLastDigits))
                .map(bo -> bo.getId())
                .forEach(System.out::println);
    }

    interface Identifiable {

        String getId();
    }

    static class Citizen implements Identifiable{

        private String name;
        private int age;
        private String id;

        public Citizen(String name, int age, String id) {
            setName(name);
            setAge(age);
            setId(id);
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        private void setAge(int age) {
            this.age = age;
        }

        private void setId(String id) {
            this.id = id;
        }

        @Override
        public String getId() {
            return this.id;
        }
    }

    static class Robot implements Identifiable {

        private String id;
        private String model;

        public Robot(String id, String model) {
            setId(id);
            setModel(model);
        }

        public String getModel() {
            return model;
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
}
