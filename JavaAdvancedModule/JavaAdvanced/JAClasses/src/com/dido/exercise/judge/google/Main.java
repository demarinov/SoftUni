package com.dido.exercise.judge.google;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Person> personList = new ArrayList<>();
        while(!"End".equals(input)) {

            String[] personData = input.split("\\s+");
            String name = personData[0];

            Person person  = findOrCreatePerson(personList, name);

            String typeOfElement = personData[1];

            switch(typeOfElement) {

                case "company":
                    // •	"<Name> company <companyName> <department> <salary>"
                    String companyName = personData[2];
                    String department = personData[3];
                    Double salary = Double.parseDouble(personData[4]);
                    Company company =new Company(companyName, department, salary);
                    person.setCompany(company);
                    break;
                case "pokemon":
                    //•	"<Name> pokemon <pokemonName> <pokemonType>"
                    String pokName = personData[2];
                    String pokType = personData[3];
                    Pokemon pok = new Pokemon(pokName, pokType);
                    person.getPokemonList().add(pok);
                    break;
                case "parents":
                    //•	"<Name> parents <parentName> <parentBirthday>"
                    String parentName = personData[2];
                    String parentBirthday = personData[3];
                    Parent parent = new Parent(parentName, parentBirthday);
                    person.getParentList().add(parent);
                    break;
                case "children":
                    //•	"<Name> children <childName> <childBirthday>"
                    String childName = personData[2];
                    String childBirthday = personData[3];
                    Child child = new Child(childName, childBirthday);
                    person.getChildList().add(child);
                    break;
                case "car":
                    //•	"<Name> car <carModel> <carSpeed>"
                    String model = personData[2];
                    Integer speed = Integer.parseInt(personData[3]);
                    Car car = new Car(model, speed);
                    person.setCar(car);
                    break;
                default:
                    break;
            }



            input = sc.nextLine();
        }

        String name = sc.nextLine();
        personList.stream().filter(p -> name.equals(p.getName())).forEach(System.out::println);
    }

    public static Person findOrCreatePerson(List<Person> personList, String name) {

        Person person = personList.stream().filter(p -> name.equals(p.getName()))
                .findAny().orElse(null);

        if (person == null) {
            person = new Person(name);
            personList.add(person);
        }

        return person;
    }

    static class Person{

        private String name;
        private Company company;
        private Car car;
        private List<Pokemon> pokemonList;
        private List<Parent> parentList;
        private List<Child> childList;


        public Person() {
        }

        public Person(String name) {
            this.name = name;
            parentList = new ArrayList<>();
            childList = new ArrayList<>();
            pokemonList = new ArrayList<>();

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Company getCompany() {
            return company;
        }

        public void setCompany(Company company) {
            this.company = company;
        }

        public Car getCar() {
            return car;
        }

        public void setCar(Car car) {
            this.car = car;
        }

        public List<Pokemon> getPokemonList() {
            return pokemonList;
        }

        public void setPokemonList(List<Pokemon> pokemonList) {
            this.pokemonList = pokemonList;
        }

        public List<Parent> getParentList() {
            return parentList;
        }

        public void setParentList(List<Parent> parentList) {
            this.parentList = parentList;
        }

        public List<Child> getChildList() {
            return childList;
        }

        public void setChildList(List<Child> childList) {
            this.childList = childList;
        }

        @Override
        public String toString() {
            // {personName}
            //	Company:
            //	{companyName} {companyDepartment} {salary}
            //	...
            //	Children:
            //	{childName} {childBirthday}
            //	{childName} {childBirthday}
            String companyStr = String.format("%s%n",getCompany());
            String carStr = String.format("%s%n",getCar());
            String pokListStr = String.format("%s%n",
                    getPokemonList().toString().replaceAll("[\\[\\]]",""))
                    .replaceAll(", ","\n");
            String parentListStr = String.format("%s%n",
                    getParentList().toString().replaceAll("[\\[\\]]",""))
                    .replaceAll(", ","\n");
            String childListStr = String.format("%s%n",
                    getChildList().toString().replaceAll("[\\[\\]]",""))
                    .replaceAll(", ","\n");

            return String.format("%s%n" +
                    "Company:%n%s" +
                    "Car:%n%s" +
                    "Pokemon:%n%s"+
                    "Parents:%n%s" +
                    "Children:%n%s",getName(), getCompany() == null ? "":companyStr
                    ,getCar() == null ? "":carStr
                    , getPokemonList().isEmpty() ? "" : pokListStr ,
                    getParentList().isEmpty() ? "" : parentListStr
                    , getChildList().isEmpty() ? "" : childListStr);
        }
    }

    //•	"<Name> children <childName> <childBirthday>"
    static class Child{

        private String name;
        private String birthday;

        public Child(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            return String.format("%s %s",getName(), getBirthday());
        }
    }

    //•	"<Name> parents <parentName> <parentBirthday>"
    static class Parent {
        private String name;
        private String birthday;

        public Parent() {
        }

        public Parent(String name, String birthday) {
            this.name = name;
            this.birthday = birthday;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        @Override
        public String toString() {
            return String.format("%s %s",getName(), getBirthday());
        }
    }

    //•	"<Name> pokemon <pokemonName> <pokemonType>"
    static class Pokemon{
        private String name;
        private String type;

        public Pokemon() {
        }

        public Pokemon(String name, String type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("%s %s",getName(), getType());
        }
    }

    //•	"<Name> car <carModel> <carSpeed>"
    static class Car{

        private String model;
        private Integer speed;

        public Car() {
        }

        public Car(String model, Integer speed) {
            this.model = model;
            this.speed = speed;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Integer getSpeed() {
            return speed;
        }

        public void setSpeed(Integer speed) {
            this.speed = speed;
        }

        @Override
        public String toString() {
            return String.format("%s %d",getModel(), getSpeed());
        }
    }

    // •	"<Name> company <companyName> <department> <salary>"
    static class Company {
        private String name;
        private String department;
        private Double salary;

        public Company() {
        }

        public Company(String name, String department, Double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return String.format("%s %s %.2f",getName(), getDepartment(), getSalary());
        }
    }
}
