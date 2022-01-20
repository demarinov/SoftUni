package com.dido.lab;

public class MultipleInheritance {

    public static void main(String[] args) {

        Puppy puppy = new Puppy();

        puppy.eat();
        puppy.bark();
        puppy.weep();
    }

    static class Animal {


        public void eat() {
            System.out.println("eating...");
        }
    }

    static class Dog extends SingleInheritance.Animal {

        public void bark() {

            System.out.println("barking...");
        }
    }

    static class Puppy extends Dog {

        public void weep() {
            System.out.println("weeping...");
        }
    }
}
