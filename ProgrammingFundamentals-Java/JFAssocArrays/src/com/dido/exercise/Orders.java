package com.dido.exercise;

import java.util.*;

public class Orders {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<Product> products = new ArrayList<>();

        while(!input.equalsIgnoreCase("buy")) {

            String[] productData = input.split("\\s+");
            String name = productData[0];
            Double price = Double.parseDouble(productData[1]);
            Integer quantity = Integer.parseInt(productData[2]);

            Product product = products.stream().filter(p -> p.getName().equals(name))
                    .findFirst().orElse(null);

            if (product == null) {
                product = new Product(name, price, quantity);
            } else {
                product.setQuantity(product.getQuantity()+quantity);
                product.setPrice(price);
            }

            products.add(product);

            input = sc.nextLine();
        }

        Map<String, Double> productMap = new LinkedHashMap<>();

        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);
            String name = product.getName();
            Double price = product.getPrice();
            Integer quantity = product.getQuantity();
            productMap.putIfAbsent(name,(price * quantity));
        }

        productMap.entrySet().stream().map(e -> String.format("%s -> %.2f",e.getKey(),e.getValue()))
                .forEach(System.out::println);
    }
}

class Product {

    private String name;
    private Double price;
    private Integer quantity;


    public Product(String name, Double price, Integer quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
