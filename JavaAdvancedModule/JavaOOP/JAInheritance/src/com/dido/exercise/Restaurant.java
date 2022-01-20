package com.dido.exercise;

import java.math.BigDecimal;
import java.util.Scanner;

public class Restaurant {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Salmon salmon = new Salmon("fish",new BigDecimal(20.5d));
        System.out.println(salmon.getName());

        Cake cake = new Cake("SweetDelight");
        System.out.println(cake.getName());
    }
}

class Cake extends Dessert {
    public static final double CAKE_GRAMS = 250d;
    public static final double CAKE_CALORIES = 1000d;
    public static final BigDecimal CAKE_PRICE = new BigDecimal(5);

    public Cake(String name) {
        super(name, CAKE_PRICE, CAKE_GRAMS, CAKE_CALORIES);
    }
}

class Salmon extends MainDish {
    public static final double SALMON_GRAMS = 22d;
    public Salmon(String name, BigDecimal price) {
        super(name, price, SALMON_GRAMS);
    }
}

class Soup extends Starter {
    public Soup(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}

class Dessert extends Food{
    private double calories;
    public Dessert(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams);
        this.calories = calories;
    }
}

class Starter extends Food {
    public Starter(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
class MainDish extends Food {
    public MainDish(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}

class Coffee extends HotBeverage {
    public static final double COFFEE_MILLILITERS = 50;
    public static final BigDecimal COFFEE_PRICE = new BigDecimal(3.50);
    private double caffeine;

    public Coffee(String name, double caffeine) {
        super(name, COFFEE_PRICE, COFFEE_MILLILITERS);
        this.caffeine = caffeine;
    }

    public double getCaffeine() {
        return caffeine;
    }

    public void setCaffeine(double caffeine) {
        this.caffeine = caffeine;
    }
}

class ColdBeverage extends Beverage{
    public ColdBeverage(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }
}

class HotBeverage extends Beverage {
    public HotBeverage(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }

}

class Food extends Product {
    private double grams;
    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }

    public void setGrams(double grams) {
        this.grams = grams;
    }
}

class Beverage extends Product {

    private double milliliters;
    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;

    }

    public double getMilliliters() {
        return milliliters;
    }

    public void setMilliliters(double milliliters) {
        this.milliliters = milliliters;
    }
}

class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
