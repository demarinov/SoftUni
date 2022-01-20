package com.dido.exercise.judge.catlady;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Cat> catsList = new LinkedList<>();

        while(!"End".equals(input)) {

            String[] catData = input.split("\\s+");
            String type = catData[0];
            String name = catData[1];
            Double value = Double.parseDouble(catData[2]);

            switch (type) {
                case "Siamese":
                    Cat cat = new Siamese(type,name, value);
                    catsList.add(cat);
                    break;
                case "Cymric":
                    cat = new Cymric(type,name,value);
                    catsList.add(cat);
                    break;
                case "StreetExtraordinaire":
                    cat = new StreetExtraordinaire(type,name,value);
                    catsList.add(cat);
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        String name = sc.nextLine();

        catsList.stream().filter(c -> name.equals(c.getName()))
                .forEach(System.out::println);
    }

    static class Siamese extends Cat {

        private Double earSize;

        public Siamese(String type, String name, Double earSize) {
            super(type, name);
            this.earSize = earSize;
        }

        public Double getEarSize() {
            return earSize;
        }

        public void setEarSize(Double earSize) {
            this.earSize = earSize;
        }

        @Override
        public String toString() {
            return String.format("%s %s %.2f",getType(), getName(), getEarSize());
        }
    }

    static class Cymric extends Cat {

        private Double furLen;

        public Cymric(Double furLen) {
            this.furLen = furLen;
        }

        public Cymric(String type, String name, Double furLen) {
            super(type, name);
            this.furLen = furLen;
        }

        public Double getFurLen() {
            return furLen;
        }

        public void setFurLen(Double furLen) {
            this.furLen = furLen;
        }

        @Override
        public String toString() {
            return String.format("%s %s %.2f",getType(), getName(), getFurLen());
        }
    }

    static class StreetExtraordinaire extends Cat {
        private Double meowDecibels;

        public StreetExtraordinaire(Double meowDecibels) {
            this.meowDecibels = meowDecibels;
        }

        public StreetExtraordinaire(String type, String name, Double meowDecibels) {
            super(type, name);
            this.meowDecibels = meowDecibels;
        }

        public Double getMeowDecibels() {
            return meowDecibels;
        }

        public void setMeowDecibels(Double meowDecibels) {
            this.meowDecibels = meowDecibels;
        }

        @Override
        public String toString() {
            return String.format("%s %s %.2f",getType(), getName(), getMeowDecibels());
        }
    }

    static class Cat {

        private String type;
        private String name;

        public Cat() {

        }

        public Cat(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Cat{" +
                    "type='" + type + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
