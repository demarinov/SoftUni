package com.dido.lab;

import java.util.Scanner;

public class SingleInheritance {

    public static void main(String[] args) {
        Dog dog = new Dog();

        dog.eat();
        dog.bark();

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
}
