package com.dido.lab;

import java.util.*;

public class SortByNameAge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] personData = sc.nextLine().split("\\s");
            Person person = new Person(personData[0], personData[1],Integer.parseInt(personData[2]));
            personList.add(person);

        }

        Collections.sort(personList,
                Comparator.comparing(Person::getFirstName).thenComparingInt(Person::getAge));
        personList.stream().forEach(System.out::println);

    }
}


class Person {
    private String firstName;
    private String lastName;
    private Integer age;

    public Person(String name, String lastName ,Integer age) {
        this.firstName = name;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("%s %s is %d years old",getFirstName(), getLastName(), getAge());
    }
}