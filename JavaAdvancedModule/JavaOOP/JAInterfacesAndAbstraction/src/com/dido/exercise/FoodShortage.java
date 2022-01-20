package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FoodShortage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());

        List<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] inputData  = sc.nextLine().split("\\s");

            if (inputData.length == 4) {
                // "<name> <age> <id> <birthdate>" for a Citizen or
                buyers.add(new Citizen(inputData[0], Integer.parseInt(inputData[1]),
                        inputData[2], inputData[3]));
            } else {
                //"<name> <age><group>"
                buyers.add(new Rebel(inputData[0], Integer.parseInt(inputData[1]), inputData[2]));
            }
        }

        String input = sc.nextLine();

        while(!"End".equals(input)) {

            String name = input;
            Buyer buyer = buyers.stream().filter(b -> name.equals(b.getName())).findFirst().orElse(null);
            
            if (buyer != null) {
                buyer.buyFood();
            }

            input = sc.nextLine();
        }

        int countFood = buyers.stream().mapToInt(b -> b.getFood()).sum();
        System.out.println(countFood);
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

    interface Buyer extends Person {

        void buyFood();
        int getFood();
    }

    static class Rebel implements Buyer {
        private String name;
        private int age;
        private String group;
        private int food;

        public Rebel(String name, int age, String group) {
            setName(name);
            setAge(age);
            setGroup(group);
            setFood(0);
        }

        private void setName(String name) {
            this.name = name;
        }

        private void setAge(int age) {
            this.age = age;
        }

        private void setGroup(String group) {
            this.group = group;
        }

        private void setFood(int food) {
            this.food = food;
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
        public void buyFood() {
            this.food += 5;
        }

        @Override
        public int getFood() {
            return this.food;
        }
    }

    static class Citizen implements Birthable, Identifiable, Buyer {

        private String name;
        private int age;
        private String id;
        private String birthDate;
        private int food;

        public Citizen(String name, int age, String id, String birthDate) {
            setName(name);
            setAge(age);
            setId(id);
            setBirthDate(birthDate);
            setFood(0);
        }

        private void setFood(int food) {
            this.food = food;
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

        @Override
        public void buyFood() {
            this.food += 10;
        }

        @Override
        public int getFood() {
            return this.food;
        }
    }
}
