package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Animals {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Animal> animalList = new ArrayList<>();
        while(true) {
            String type = sc.nextLine();

            if ("Beast!".equals(type)) {
                break;
            }

            Animal animal;
            String[] animalData = sc.nextLine().split("\\s");
            Integer age = Integer.parseInt(animalData[1]);
            String name = animalData[0];
            String gender = animalData[2];

            try {
                if (age < 0) {
                    throw new Exception("Invalid input!");
                }

                if (!"Female".equals(gender) && !"Male".equals(gender)) {
                    throw new Exception("Invalid input!");
                }

                switch (type) {

                    case "Dog":
                        animal = new Dog(name, age, gender);
                        animalList.add(animal);
                        break;
                    case "Cat":
                        animal = new Cat(name, age, gender);
                        animalList.add(animal);
                        break;
                    case "Frog":
                        animal = new Frog(name, age, gender);
                        animalList.add(animal);
                        break;
                    case "Kittens":
                        animal = new Kittens(name, age);
                        animalList.add(animal);
                        break;
                    case "Tomcat":
                        animal = new Tomcat(name, age);
                        animalList.add(animal);
                        break;

                    default:
                        throw new Exception("Invalid input!");
                }

            }catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }

        animalList.stream().forEach(e ->
                System.out.printf("%s%n%s%n%s%n",
                        e.getClass().getSimpleName(),e ,e.produceSound()));
    }
}

class Tomcat extends Cat {
    public static final String GENDER= "Male";
    public Tomcat(String name, Integer age) {
        super(name, age, GENDER);
    }

    @Override
    public String produceSound() {
        return "MEOW";
    }
}

class Kittens extends Cat{
    public static final String GENDER= "Male";
    public Kittens(String name, Integer age) {
        super(name, age, GENDER);
    }

    @Override
    public String produceSound() {
        return "Meouw";
    }
}

class Frog extends Animal{
    public Frog(String name, Integer age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Ribbit";
    }
}

class Dog extends Animal {
    public Dog(String name, Integer age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Woof!";
    }
}

class Cat extends Animal {
    public Cat(String name, Integer age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Meow meow";
    }
}

class Animal {

    private String name;
    private Integer age;
    private String gender;

    public Animal(String name, Integer age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String produceSound() {

        return "Brauuu";
    }

    @Override
    public String toString() {
        return name + " " + age + " "+ gender;
    }
}
