package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PizzaCalories {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] pizzaData = sc.nextLine().split("\\s");
        String[] doughData = sc.nextLine().split("\\s");

        try {

            int toppingsNumber = Integer.parseInt(pizzaData[2]);
            Pizza pizza = new Pizza(pizzaData[1], toppingsNumber);
            Dough dough = new Dough(doughData[1], doughData[2], Double.parseDouble(doughData[3]));
            pizza.setDough(dough);

            String input = sc.nextLine();
            List<Topping> toppingList = new ArrayList<>();
            while(!"END".equals(input)) {
                String[] toppingsData = input.split("\\s");

                Topping topping = new Topping(toppingsData[1],Double.parseDouble(toppingsData[2]));
                toppingList.add(topping);

                input = sc.nextLine();
            }

            toppingList.stream().limit(toppingsNumber).forEach(t -> pizza.addTopping(t));
            System.out.println(pizza);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    static class Topping {

        private String toppingType;
        private double weight;

        public Topping(String toppingType, double weight) {
            setToppingType(toppingType);
            setWeight(weight);
        }

        public String getToppingType() {
            return toppingType;
        }

        private void setToppingType(String toppingType) {
            if (!"Meat".equals(toppingType) && !"Veggies".equals(toppingType)
                    && !"Cheese".equals(toppingType) && !"Sauce".equals(toppingType)) {
                throw new IllegalArgumentException("Cannot place "
                        +toppingType+" on top of your pizza.");
            }

            this.toppingType = toppingType;
        }

        public double getWeight() {
            return weight;
        }

        private void setWeight(double weight) {

            if (weight < 0 || weight > 50) {
                throw new IllegalArgumentException(this.toppingType+" weight should be in the range [1..50].");
            }
            this.weight = weight;
        }

        public double calculateCalories() {

            double modifier = 1d;

            switch(this.toppingType) {

                case "Meat":
                    modifier *= 1.2d;
                    break;
                case "Veggies":
                    modifier *= 0.8d;
                    break;
                case "Cheese":
                    modifier *= 1.1d;
                    break;
                case "Sauce":
                    modifier *= 0.9d;
                    break;
            }

            double calories = (2.0d * weight) * modifier;

            return calories;

        }
    }

    static class Dough {

        private String flourType;
        private String bakingTechnique;
        private double weight;

        public Dough(String flourType, String bakingTechnique, double weight) {

            setFlourType(flourType);
            setBakingTechnique(bakingTechnique);
            setWeight(weight);
        }

        public String getFlourType() {
            return flourType;
        }

        private void setFlourType(String flourType) {
            if (!"White".equals(flourType) && !"Wholegrain".equals(flourType)) {
                throw new IllegalArgumentException("Invalid type of dough.");
            }
            this.flourType = flourType;
        }

        public String getBakingTechnique() {
            return bakingTechnique;
        }

        private void setBakingTechnique(String bakingTechnique) {

            if (!"Crispy".equals(bakingTechnique)
                    && !"Chewy".equals(bakingTechnique)
                    && !"Homemade".equals(bakingTechnique)) {
                throw new IllegalArgumentException("Invalid type of dough.");
            }

            this.bakingTechnique = bakingTechnique;
        }

        public double getWeight() {
            return weight;
        }

        private void setWeight(double weight) {

            if (weight < 0 || weight > 200) {
                throw new IllegalArgumentException("Dough weight should be in the range [1..200].");
            }
            this.weight = weight;
        }

        public double calculateCalories() {

            double modifier = 1;

            switch(this.flourType) {
                case "White":
                    modifier = 1.5;
                    break;
                case "Wholegrain":
                    modifier = 1.0;
                    break;
                default:
                    break;
            }

            switch(this.bakingTechnique) {

                case "Crispy":
                    modifier *= 0.9d;
                    break;
                case "Chewy":
                    modifier *= 1.1d;
                    break;
                case "Homemade":
                    modifier *= 1.0d;
                    break;
                default:
                    break;
            }

            // 2 cals per gram
            double calories = (2.0d * weight) * modifier;

            return calories;
        }
    }

    static class Pizza {
        private String name;
        private Dough dough;
        private List<Topping> toppingList;

        public Pizza(String name, int numberOfToppings) {
            this.name = name;
            setToppings(numberOfToppings);

        }

        public String getName() {
            return name;
        }

        private void setName(String name) {

            if (name.trim().isEmpty() || name.length() > 15) {
                throw new IllegalArgumentException("Pizza name should be between 1 and 15 symbols.");
            }
            this.name = name;
        }

        public Dough getDough() {
            return dough;
        }

        public void setDough(Dough dough) {
            this.dough = dough;
        }

        public void addTopping(Topping topping) {
            toppingList.add(topping);
        }

        private void setToppings(int toppings) {
            if (toppings < 0 || toppings > 10) {
                throw new IllegalArgumentException("Number of toppings should be in range [0..10].");
            }
            this.toppingList = new ArrayList<>(toppings);
        }

        public double getOverallCalories() {

            double totalCalories = 0.0d;
            double doughCalories = dough.calculateCalories();
            double toppingsCalories = toppingList.stream().mapToDouble(t -> t.calculateCalories()).sum();

            totalCalories = doughCalories + toppingsCalories;

            return totalCalories;
        }

        @Override
        public String toString() {
            return String.format("%s - %.2f",this.getName(), this.getOverallCalories());
        }
    }
}
