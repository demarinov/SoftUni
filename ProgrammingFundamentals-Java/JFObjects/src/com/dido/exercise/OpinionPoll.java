package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class OpinionPoll {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lines = Integer.parseInt(sc.nextLine());
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < lines; i++) {

            String[] personData = sc.nextLine().split("\\s+");
            Person person = new Person(personData[0], Integer.parseInt(personData[1]));
            people.add(person);
        }

        // print over 30 sorted
        List<Person> sortedPeople = people.stream().filter(p -> p.age > 30).sorted().collect(Collectors.toList());
//        List<Person> sortedPeople = people.stream().filter(p -> p.age > 30)
//                .sorted((o1,o2) -> o1.name.compareTo(o2.name)).collect(Collectors.toList());

        for (int i = 0; i < sortedPeople.size(); i++) {
            Person person = sortedPeople.get(i);
            System.out.printf("%s - %d%n",person.getName(),person.getAge());
        }
    }
}

class Person implements Comparable {

    String name;
    Integer age;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) &&
                Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {

        return this.name.compareTo(((Person)o).name);
    }
}
