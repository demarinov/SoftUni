package com.dido.exercise.judge.opinionpoll;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Person> personList = new LinkedList<>();
        for (int i = 0; i < n; i++) {

            String[] personData = sc.nextLine().split("\\s+");

            Person person = new Person();
            String name = personData[0];
            Integer age = Integer.parseInt(personData[1]);

            person.setName(name);
            person.setAge(age);
            personList.add(person);
        }

        Predicate<Person> agePredicate = a -> a.getAge() > 30;

        personList.stream().filter(agePredicate)
                .sorted()
                .forEach(p -> System.out.printf("%s - %d%n",p.getName(),p.getAge()));
    }

    static class Person implements Comparable{

        private String name;
        private Integer age;

        public Person() {

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
        public int compareTo(Object o) {
            return this.getName().compareTo(((Person) o).getName());
        }
    }
}
