package com.dido.exercise;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

public class MultipleImplementation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Class[] citizenInterfaces = Citizen.class.getInterfaces();
        if (Arrays.asList(citizenInterfaces).contains(Birthable.class)
                && Arrays.asList(citizenInterfaces).contains(Identifiable.class)) {
            Method[] methods = Birthable.class.getDeclaredMethods();
            methods = Identifiable.class.getDeclaredMethods();
            Scanner scanner = new Scanner(System.in);
            String name = scanner.nextLine();
            int age = Integer.parseInt(scanner.nextLine());
            String id = scanner.nextLine();
            String birthDate = scanner.nextLine();
            Identifiable identifiable = new Citizen(name,age,id,birthDate);
            Birthable birthable = new Citizen(name,age,id,birthDate);
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
            System.out.println(methods.length);
            System.out.println(methods[0].getReturnType().getSimpleName());
        }


    }

    interface Birthable{
        String getBirthDate();
    }

    interface Identifiable {

        String getId();
    }

    interface Person {

        String getName();
        int getAge();
    }

    static class Citizen implements Person,Birthable,Identifiable {

        private String name;
        private int age;
        private String id;
        private String birthDate;

        public Citizen(String name, int age, String id, String birthDate) {
            setName(name);
            setAge(age);
            setId(id);
            setBirthDate(birthDate);
        }

        private void setId(String id) {
            this.id = id;
        }

        private void setBirthDate(String birthDate) {
            this.birthDate = birthDate;
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


        @Override
        public String getBirthDate() {
            return null;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public String toString() {
            return "Citizen{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", id='" + id + '\'' +
                    ", birthDate='" + birthDate + '\'' +
                    '}';
        }
    }
}
