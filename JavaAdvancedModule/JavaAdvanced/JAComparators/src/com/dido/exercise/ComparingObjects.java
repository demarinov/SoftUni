package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ComparingObjects {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String input = sc.nextLine();

        List<Person> personList = new ArrayList<>();
        while(!"END".equals(input)) {

            String[] personData = input.split("\\s");

            String name = personData[0];
            Integer age = Integer.parseInt(personData[1]);
            String town = personData[2];
            Person person = new Person(name, age, town);

            personList.add(person);
            input = sc.nextLine();
        }

        int n = Integer.parseInt(sc.nextLine());

        if (n >=0 && n <= personList.size()-1) {

            Person searchedPerson = personList.get(n);

            // {number of equal people} {number of not equal people} {total number of people}
            int countEqualPeople = personList.stream().filter(s -> searchedPerson.compareTo(s) == 0)
                    .collect(Collectors.toList()).size();

            if (countEqualPeople == 0) {
                System.out.println("No matches");
            } else {
                int countNotEqualPeople = personList.stream().filter(s -> searchedPerson.compareTo(s) != 0)
                        .collect(Collectors.toList()).size();
                int totalNumber = countEqualPeople + countNotEqualPeople;

                System.out.printf("%d %d %d%n",countEqualPeople,countNotEqualPeople, totalNumber);
            }
        } else {
            System.out.println("No matches");
        }
    }

}

class Person implements Comparable<Person> {

    private String name;
    private Integer age;
    private String town;

    public Person() {

    }

    public Person(String name, Integer age, String town) {
        this.name = name;
        this.age = age;
        this.town = town;
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

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }


    @Override
    public int compareTo(Person o) {

        int result = this.name.compareTo(o.getName());
        if (result == 0) {
            result = this.age.compareTo(o.getAge());

            if (result == 0) {
                return this.town.compareTo(o.getTown());
            } else {
                return result;
            }
        } else {
            return result;
        }
    }
}
