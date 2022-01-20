package com.dido.lab;

import java.util.ArrayList;
import java.util.List;

public class SayHello {

    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();

        persons.add(new Bulgarian("Pesho"));
        persons.add(new European("Pesho"));
        persons.add(new Chinese("Pesho"));

        for (Person person : persons) {
            print(person);
        }

    }

    private static void print(Person person) {
        System.out.println(person.sayHello());
    }

    interface Person {

        String getName();
        default String sayHello(){
            return "Hello";
        }
    }

    static class Chinese implements Person {

        private String name;

        public Chinese(String name) {
            setName(name);
        }

        private void setName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String sayHello() {
            return "Djydjybydjy";
        }
    }

    static class European implements Person {

        private String name;

        public European(String name) {
            setName(name);
        }

        private void setName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }
    }

    static class Bulgarian implements Person {

        private String name;

        public Bulgarian(String name) {
            setName(name);
        }

        private void setName(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String sayHello() {
            return "Здравей";
        }
    }
}
