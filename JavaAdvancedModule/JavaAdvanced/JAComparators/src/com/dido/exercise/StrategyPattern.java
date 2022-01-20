package com.dido.exercise;

import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

public class StrategyPattern {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StrategyPattern strategy = new StrategyPattern();
        int n = Integer.parseInt(sc.nextLine());

        Comparator<Person> comparatorName = strategy.createComparator("Name");
        Comparator<Person> comparatorAge = strategy.createComparator("Age");

        TreeSet<Person> personTreeSetName = new TreeSet<>(comparatorName);
        TreeSet<Person> personTreeSetAge = new TreeSet<>(comparatorAge);
        for (int i = 0; i < n; i++) {
            String[] personData = sc.nextLine().split("\\s+");
            String name = personData[0];
            Integer age = Integer.parseInt(personData[1]);

            Person personOne = strategy.createPerson(name,age);

            personTreeSetName.add(personOne);
            personTreeSetAge.add(personOne);
        }


        personTreeSetName.forEach(System.out::println);
        personTreeSetAge.forEach(System.out::println);
    }

    private final class ComparatorName implements Comparator<Person> {


        @Override
        public int compare(Person o1, Person o2) {

            int result = Integer.valueOf(o1.getName().length()).compareTo(o2.getName().length());

            return result == 0 ? Character.valueOf(o1.getName().toLowerCase().charAt(0))
                    .compareTo(o2.getName().toLowerCase().charAt(0)) : result;
        }
    }

    private final class ComparatorAge implements Comparator<Person> {


        @Override
        public int compare(Person o1, Person o2) {

            int result = o1.getAge().compareTo(o2.getAge());
            return result;
        }
    }

    public Comparator<Person> createComparator(String type) {

        switch(type) {

            case "Name":
                return new ComparatorName();
            case "Age":
                return new ComparatorAge();
        }

        return null;
    }

    private final class Person {

        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return name + " "+age;
        }
    }

    public Person createPerson(String name, Integer age) {
        return new Person(name, age);
    }
}
