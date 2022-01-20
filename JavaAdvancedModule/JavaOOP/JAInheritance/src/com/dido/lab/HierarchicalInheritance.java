package com.dido.lab;

public class HierarchicalInheritance {

    public static void main(String[] args) {

        Dog dog =new Dog();
        dog.eat();
        dog.bark();

        Cat cat = new Cat();
        cat.eat();
        cat.meow();
    }

    static class Animal {


        public void eat() {
            System.out.println("eating...");
        }
    }

    static class Dog extends Animal {

        public void bark() {

            System.out.println("barking...");
        }
    }

    static class Cat extends Animal {

        public void meow() {
            System.out.println("meowing...");
        }
    }
}
