package com.dido.lab;

import java.util.ArrayList;
import java.util.List;

public class SayHelloExtended {

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

    static abstract class BasePerson implements Person {

        private String name;

        protected BasePerson(String name) {
            setName(name);
        }

        @Override
        public String getName() {
            return name;
        }

        private void setName(String name) {
            this.name = name;
        }
    }

    static class Chinese extends BasePerson {

        public Chinese(String name) {
            super(name);
        }

        @Override
        public String sayHello() {
            return "Djydjybydjy";
        }
    }

    static class European extends BasePerson{

        private String name;

        public European(String name) {
            super(name);
        }
    }

    static class Bulgarian extends BasePerson {

        private String name;

        public Bulgarian(String name) {
            super(name);
        }

        @Override
        public String sayHello() {
            return "Здравей";
        }
    }

}
