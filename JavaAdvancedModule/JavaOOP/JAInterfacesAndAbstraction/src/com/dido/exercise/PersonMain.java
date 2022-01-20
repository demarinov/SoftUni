package com.dido.exercise;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class PersonMain {

    public static void main(String[] args) {

        Class[] citizenInterfaces = Citizen.class.getInterfaces();
        if(Arrays.asList(citizenInterfaces).contains(Person.class)){
            Method[] fields = Person.class.getDeclaredMethods();
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            Person person = new Citizen(name,age);
            System.out.println(fields.length);
            System.out.println(person.getName());
            System.out.println(person.getAge());
        }

    }

    interface Person {

        String getName();
        int getAge();
    }

    static class Citizen implements Person {

        private String name;
        private int age;

        public Citizen(String name, int age) {
            setName(name);
            setAge(age);
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


    }
}
