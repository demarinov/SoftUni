package com.dido.exercise;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WildFarm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Animal> animalList = new ArrayList<>();

        String input = sc.nextLine();

        while (!"End".equals(input)) {

            String[] animalData = input.split("\\s");
            // {AnimalType} {AnimalName} {AnimalWeight} {AnimalLivingRegion}
            // [{CatBreed} = Only if its cat]

            String[] foodData = sc.nextLine().split("\\s");

            String foodType = foodData[0];
            Integer foodQuantity = Integer.parseInt(foodData[1]);

            Food food = null;
            if ("Vegetable".equals(foodType)) {
                food = new Vegetable(foodQuantity);
            } else if ("Meat".equals(foodType)) {
                food = new Meat(foodQuantity);
            }

            Animal animal = null;
            String animalType = animalData[0];
            StringBuilder foodMessage = new StringBuilder();

            switch(animalType) {

                case "Cat":
                    String name = animalData[1];
                    Double weight = Double.parseDouble(animalData[2]);
                    String region = animalData[3];
                    String breed = animalData[4];
                    animal = new Cat(animalType,name,weight,region,breed);

                    break;
                case "Tiger":
                    name = animalData[1];
                    weight = Double.parseDouble(animalData[2]);
                    region = animalData[3];
                    animal = new Tiger(animalType,name,weight,region);
                    if ("Vegetable".equals(foodType)) {
                        foodMessage
                                .append(String.format("%s are not eating that type of food!",animalType));
                        food = null;
                    }
                    break;
                case "Zebra":
                    name = animalData[1];
                    weight = Double.parseDouble(animalData[2]);
                    region = animalData[3];
                    animal = new Zebra(animalType,name,weight,region);

                    if ("Meat".equals(foodType)) {
                        foodMessage
                                .append(String.format("%s are not eating that type of food!",animalType));
                        food = null;
                    }
                    break;
                case "Mouse":
                    name = animalData[1];
                    weight = Double.parseDouble(animalData[2]);
                    region = animalData[3];
                    animal = new Mouse(animalType,name,weight,region);
                    if ("Meat".equals(foodType)) {
                        foodMessage
                                .append(String.format("%s are not eating that type of food!",animalType));
                        food = null;
                    }
                    break;
                default:
                    break;

            }

            if (animal != null) {
                animal.makeSound();
                if (food != null) {
                    animal.eat(food);
                } else {
                    System.out.println(foodMessage.toString());
                }

                animalList.add(animal);
            }

            input = sc.nextLine();
        }

        animalList.stream().forEach(System.out::println);
     }

    static abstract class Animal {
        private String animalName;
        private String animalType;
        private Double animalWeight;
        private Integer foodEaten;

        public Animal(String animalName, String animalType, Double animalWeight) {
            setAnimalName(animalName);
            setAnimalType(animalType);
            setAnimalWeight(animalWeight);
            setFoodEaten(0);
        }

        public Animal(String animalName, String animalType, Double animalWeight, Integer foodEaten) {
            setAnimalName(animalName);
            setAnimalType(animalType);
            setAnimalWeight(animalWeight);
            setFoodEaten(foodEaten);
        }

        public abstract void makeSound();
        public abstract void eat(Food food);

        public String getAnimalName() {
            return animalName;
        }

        private void setAnimalName(String animalName) {
            this.animalName = animalName;
        }

        public String getAnimalType() {
            return animalType;
        }

        private void setAnimalType(String animalType) {
            this.animalType = animalType;
        }

        public Double getAnimalWeight() {
            return animalWeight;
        }

        private void setAnimalWeight(Double animalWeight) {
            this.animalWeight = animalWeight;
        }

        public Integer getFoodEaten() {
            return foodEaten;
        }

        protected void setFoodEaten(Integer foodEaten) {
            this.foodEaten = foodEaten;
        }
    }

    static abstract class Food {

        private Integer quantity;

        public Food(Integer quantity) {
            setQuantity(quantity);
        }

        public Integer getQuantity() {
            return quantity;
        }

        private void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    static class Meat extends Food{

        public Meat(Integer quantity) {
            super(quantity);
        }
    }

    static class Vegetable extends Food {

        public Vegetable(Integer quantity) {
            super(quantity);
        }
    }

    static abstract class Mammal extends Animal {

        private String livingRegion;

        public Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
            super(animalName, animalType, animalWeight);
            setLivingRegion(livingRegion);
        }

        public Mammal(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
            super(animalName, animalType, animalWeight, foodEaten);
            setLivingRegion(livingRegion);
        }

        public String getLivingRegion() {
            return livingRegion;
        }

        protected void setLivingRegion(String livingRegion) {
            this.livingRegion = livingRegion;
        }
    }

    static class Zebra extends Mammal {

        public Zebra(String animalType,String animalName, Double animalWeight, String livingRegion) {
            super(animalName, animalType, animalWeight, livingRegion);
        }

        public Zebra(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
            super(animalName, animalType, animalWeight, foodEaten, livingRegion);
        }

        @Override
        public void makeSound() {
            System.out.println("Zs");
        }

        @Override
        public void eat(Food food) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        }

        @Override
        public String toString() {
            DecimalFormat format = new DecimalFormat("#.##");

            return String.format("%s [%s, " +
                            "%s, %s, %d]",
                    getAnimalType(),getAnimalName(),format.format(getAnimalWeight()),
                    getLivingRegion(),getFoodEaten());
        }
    }

    static class Mouse extends Mammal {

        public Mouse(String animalType,String animalName, Double animalWeight, String livingRegion) {
            super(animalName, animalType, animalWeight, livingRegion);
        }

        public Mouse(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
            super(animalName, animalType, animalWeight, foodEaten, livingRegion);
        }

        @Override
        public void makeSound() {
            System.out.println("SQUEEEAAAK!");
        }

        @Override
        public void eat(Food food) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        }

        @Override
        public String toString() {
            DecimalFormat format = new DecimalFormat("#.##");

            return String.format("%s [%s, " +
                            "%s, %s, %d]",
                    getAnimalType(),getAnimalName(),format.format(getAnimalWeight()),
                    getLivingRegion(),getFoodEaten());
        }
    }

    static class Tiger extends Felime {

        private String livingRegion;

        public Tiger(String animalType,String animalName, Double animalWeight,
                     String livingRegion) {
            super(animalName, animalType, animalWeight, livingRegion);

        }

        public Tiger(String animalName, String animalType, Double animalWeight,
                     Integer foodEaten, String livingRegion) {
            super(animalName, animalType, animalWeight, foodEaten, livingRegion);

        }

        @Override
        public String getLivingRegion() {
            return livingRegion;
        }

        @Override
        protected void setLivingRegion(String livingRegion) {
            this.livingRegion = livingRegion;
        }

        @Override
        public void makeSound() {
            System.out.println("ROAAR!!!");
        }

        @Override
        public void eat(Food food) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        }

        @Override
        public String toString() {
            DecimalFormat format = new DecimalFormat("#.##");

            return String.format("%s [%s, " +
                            "%s, %s, %d]",
                    getAnimalType(),getAnimalName(),format.format(getAnimalWeight()),
                    getLivingRegion(),getFoodEaten());
        }
    }

    static class Cat extends Felime {

        private String breed;

        public Cat(String animalType,String animalName, Double animalWeight,
                   String livingRegion, String breed) {
            super(animalName, animalType, animalWeight, livingRegion);
            setBreed(breed);
        }

        public Cat(String animalName, String animalType, Double animalWeight, Integer foodEaten,
                   String livingRegion, String breed) {
            super(animalName, animalType, animalWeight, foodEaten, livingRegion);
            setBreed(breed);
        }

        public String getBreed() {
            return breed;
        }

        private void setBreed(String breed) {
            this.breed = breed;
        }

        @Override
        public void makeSound() {
            System.out.println("Meowwww");
        }

        @Override
        public void eat(Food food) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        }

        @Override
        public String toString() {
            DecimalFormat format = new DecimalFormat("#.##");

            return String.format("%s [%s, %s, " +
                    "%s, %s, %d]",
                    getAnimalType(),getAnimalName(),getBreed(),format.format(getAnimalWeight()),
                    getLivingRegion(),getFoodEaten());
        }
    }

    static abstract class Felime extends Mammal {

        public Felime(String animalName, String animalType, Double animalWeight, String livingRegion) {
            super(animalName, animalType, animalWeight, livingRegion);
        }

        public Felime(String animalName, String animalType, Double animalWeight, Integer foodEaten, String livingRegion) {
            super(animalName, animalType, animalWeight, foodEaten, livingRegion);
        }

    }
}
