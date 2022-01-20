package com.dido.exercise;

import java.util.*;

public class EqualityLogic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        EqualityLogic equalityLogic = new EqualityLogic();
        int n = Integer.parseInt(sc.nextLine());

        TreeSet<Person> personTreeSet = new TreeSet<>();
        HashSet<Person> personHashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {

            String[] personData = sc.nextLine().split("\\s+");
            String name = personData[0];
            Integer age = Integer.parseInt(personData[1]);

            Person personOne = equalityLogic.createPerson(name,age);

            personTreeSet.add(personOne);
            personHashSet.add(personOne);
        }

        System.out.println(personTreeSet.size());
        System.out.println(personHashSet.size());
    }

    private final class Person implements Comparable<Person> {

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(name, person.name) && Objects.equals(age, person.age);
        }

        @Override
        public int hashCode() {
            int number = 20;
            int result = 1;
            result += ((number * name.hashCode()) + age.hashCode());
            return result;
        }

        @Override
        public int compareTo(Person o) {
            int result = o.getName().compareTo(this.getName());
            return result == 0 ? o.getAge().compareTo(this.getAge()) : result;
        }
    }

    public Person createPerson(String name, Integer age) {
        return new Person(name, age);
    }
}
