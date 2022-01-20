package com.dido.lab;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FirstAndReserveTeam {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] personData = sc.nextLine().split("\\s");
            Person person = new Person();

            try {
                person.setFirstName(personData[0]);
                person.setLastName(personData[1]);
                person.setAge(Integer.parseInt(personData[2]));
                person.setSalary(Double.parseDouble(personData[3]));
                people.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        Team team = new Team("Black Eagles");
        for (Person person : people) {
            team.addPlayer(person);
        }

        System.out.println("First team have "+team.getFirstTeam().size()+" players");
        System.out.println("Reserve team have "+team.getReserveTeam().size()+" players");

    }

    static class Team{

        private String name;
        private List<Person> firstTeam;
        private List<Person> reserveTeam;


        public Team(String name) {
            this.name = name;
            firstTeam = new ArrayList<>();
            reserveTeam = new ArrayList<>();
        }

        public void addPlayer(Person person) {
            if (person.getAge() < 40) {
                firstTeam.add(person);
            } else {
                reserveTeam.add(person);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Person> getFirstTeam() {
            return Collections.unmodifiableList(firstTeam);
        }


        public List<Person> getReserveTeam() {
            return Collections.unmodifiableList(reserveTeam);
        }

    }

    static class Person {
        private String firstName;
        private String lastName;
        private Integer age;
        private double salary;

        public Person() {

        }

        private Person(String name, String lastName ,Integer age, double salary) {
            this.firstName = name;
            this.lastName = lastName;
            this.age = age;
            this.salary = salary;
        }

        public double salary() {
            return salary;
        }

        // •Names must be at least 3 symbols
        //•	Age must not be zero or negative
        //•	Salary can't be less than 460.0

        public void setSalary(double salary) {

            if (salary < 460) {
                throw new IllegalArgumentException("Salary cannot be less than 460 leva");
            }
            this.salary = salary;
        }

        public void increaseSalary(double bonus) {

            if (this.age < 30) {
                bonus /= 2;
            }

            salary += (salary * bonus/100);
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            if (firstName.length() < 3) {
                throw new IllegalArgumentException("First name cannot be less than 3 symbols");
            }
            this.firstName = firstName;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {

            if (age.intValue() <= 0) {
                throw new IllegalArgumentException("Age cannot be zero or negative integer");
            }
            this.age = age;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {

            if (lastName.length() < 3) {
                throw new IllegalArgumentException("Last name cannot be less than 3 symbols");
            }
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            DecimalFormat formatter = new DecimalFormat("0.0##");
            return String.format("%s %s gets %s leva",getFirstName(), getLastName()
                    , formatter.format(salary()));
        }
    }
}
