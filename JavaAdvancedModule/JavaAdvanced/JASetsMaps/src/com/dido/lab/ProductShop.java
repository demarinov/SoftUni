package com.dido.lab;

import java.util.*;

public class ProductShop {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program that prints information about food shops in Sofia and
//        the products they store. Until the "Revision" command you will receive
//        an input in the format: "{shop}, {product}, {price}"
//        Take in mind that if you receive a shop you already have received,
//        you must collect its product information.
//                Your output must be ordered by shop name and must be in the format:
//        {shop}->
//        Product: {product}, Price: {price}
//        The price should be formated to one digit after the decimal point.

        String input = sc.nextLine();

        Map<String, List<Product>> shopMap = new TreeMap<>();
        while(!input.equals("Revision")) {

            String[] shopData = input.split(",\\s");
            String shop = shopData[0];
            String product = shopData[1];
            Double price = Double.parseDouble(shopData[2]);

            shopMap.putIfAbsent(shop,new ArrayList<>());
            Product productObj = new Product(product, price);
            List<Product> products = shopMap.get(shop);
            products.add(productObj);

            input = sc.nextLine();
        }

        shopMap.entrySet().stream()
                .map(e ->
                {
                    String productValues = e.getValue().stream()
                            .map(p -> String.format("Product: %s, Price: %.1f%n",p.getName(), p.getPrice()))
                            .reduce("",String::concat);
                    return String.format("%s->%n%s",e.getKey(),productValues);
                })
                .forEach(System.out::print);
    }

    static class Product {

        private String name;
        private Double price;

        public Product(String name, Double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }
    }
}
