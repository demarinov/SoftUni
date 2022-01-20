package com.dido.exercise;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ShoppingSpree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] personData = sc.nextLine().split(";");
        List<Person> personList = new LinkedList<>();
        for (int i = 0; i < personData.length; i++) {
            String[] data = personData[i].split("=");
            String name = data[0];
            double money = Double.parseDouble(data[1]);

            try {
                Person person = new Person(name, money);
                personList.add(person);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }

        }

        String[] productData = sc.nextLine().split(";");
        List<Product> productList = new LinkedList<>();
        for (int i = 0; i < productData.length; i++) {

            String[] data = productData[i].split("=");
            String name = data[0];
            double cost = Double.parseDouble(data[1]);

            try {
                Product product = new Product(name, cost);
                productList.add(product);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
                return;
            }
        }

        String input = sc.nextLine();

        while (!"END".equals(input)) {
            String[] data = input.split("\\s+");

            String personName = data[0];
            String productName = data[1];

            Person person = findPerson(personList, personName);
            Product product = findProduct(productList, productName);

            try {
                person.buyProduct(product);
                System.out.printf("%s bought %s%n",person.getName(), product.getName());

            } catch (ArithmeticException e) {
                System.out.println(e.getLocalizedMessage());
            }
            input = sc.nextLine();
        }

        personList.stream().map(p -> {
            String personName = p.getName();
            List<Product> products = p.getProductList();
            String productResult = products.stream().map(pr -> String.format("%s,",pr.getName()))
                    .reduce("",String::concat);
            if (productResult.isEmpty()) {
                return String.format("%s - %s",personName, "Nothing bought");
            }
            return String.format("%s - %s",personName, productResult.substring(0,productResult.length()-1));
        }).forEach(System.out::println);
    }

    public static Product findProduct(List<Product> productList, String productName) {

        Product product = productList.stream().filter(p -> productName.equals(p.getName()))
                .findFirst().orElse(null);

        return product;
    }

    public static Person findPerson(List<Person> personList, String personName) {

        Person person = personList.stream().filter(n -> personName.equals(n.getName()))
                .findFirst().orElse(null);

        return person;
    }

    static class Product {

        private String name;
        private double cost;

        public Product(String name, double cost) {
            setName(name);
            setCost(cost);
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }

            this.name = name;
        }

        public double getCost() {
            return cost;
        }

        private void setCost(double cost) {
            this.cost = cost;
        }
    }

    static class Person {
        private String name;
        private double money;
        private List<Product> productList;

        public Person(String name, double money) {
            setName(name);
            setMoney(money);
            productList = new LinkedList<>();
        }

        public void buyProduct(Product product) {


            if (getMoney() < product.getCost()) {

                throw new ArithmeticException
                        (String.format("%s can't afford %s", this.getName(), product.getName()));
            }
            // to do
            productList.add(product);
            setMoney(getMoney()-product.getCost());
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be empty");
            }
            this.name = name;
        }

        private double getMoney() {
            return money;
        }

        private void setMoney(double money) {

            if (money < 0) {
                throw new IllegalArgumentException("Money cannot be negative");
            }
            this.money = money;
        }

        public List<Product> getProductList() {
            return Collections.unmodifiableList(productList);
        }
    }
}
