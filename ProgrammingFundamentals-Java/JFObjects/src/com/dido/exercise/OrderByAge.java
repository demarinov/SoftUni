package com.dido.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class OrderByAge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<NewPerson> people = new ArrayList<>();

        while(!input.equalsIgnoreCase("end")) {

            String[] personData = input.split("\\s+");
            people.add(new NewPerson(personData[0], personData[1], Integer.parseInt(personData[2])));

            input = sc.nextLine();
        }

        // sort people by age
        Collections.sort(people, (o1,o2) -> o1.age.compareTo(o2.age));

        for (int i = 0; i < people.size(); i++) {
            System.out.println(people.get(i).toString());
        }
    }
}

class NewPerson {

    String name;
    String id;
    Integer age;

    public NewPerson(String name, String id, Integer age) {

        this.name = name;
        this.id = id;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " with ID: " + id + " is "+ age +" years old.";
    }
}
