package com.dido.exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// should be reworked a bit.... on error printing and rarity should be Integer for the sorting
public class PlantDiscovery {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        List<Plant> plantList = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            String[] plantData = sc.nextLine().split("<->");

            String plantName = plantData[0];
            String rarity = plantData[1];
            Plant plant = new Plant(plantName, rarity, new ArrayList<>());
            plantList.add(plant);
        }

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("exhibition")) {

//            •	Rate: {plant} - {rating} – add the given rating to the plant (store all ratings)
//•	Update: {plant} - {new_rarity} – update the rarity of the plant with the new one
//•	Reset: {plant} – remove all the ratings of the given plant
            String[] commandData = command.split(": ");

            String instruction = commandData[0];
            String[] valueData = commandData[1].split(" - ");

            if (instruction.equalsIgnoreCase("rate")) {
                ratePlant(plantList, valueData[0], valueData[1]);
            } else if (instruction.equalsIgnoreCase("update")) {
                updatePlant(plantList, valueData[0], valueData[1]);
            } else if (instruction.equalsIgnoreCase("reset")) {
                resetPlant(plantList, commandData[1]);
            } else {
                System.out.println("error");
            }

            command = sc.nextLine();
        }

        calculatePlantsAverageRating(plantList);
        printPlants(plantList);
    }

    public static void resetPlant(List<Plant> plants, String plantName) {
        Plant plant = plants.stream().filter(p -> p.getName().equals(plantName)).findFirst().orElse(null);

        if (plant != null) {
            plant.removeAllRatings();
        }
    }

    public static void updatePlant(List<Plant> plants, String plantName, String rarity) {

        Plant plant = plants.stream().filter(p -> p.getName().equals(plantName)).findFirst().orElse(null);

        if (plant != null) {
            plant.setRarity(rarity);
        }
        // else print error
    }

    public static void ratePlant(List<Plant> plants, String plantName, String rating) {

        Plant plant = plants.stream().filter(p -> p.getName().equals(plantName)).findFirst().orElse(null);

        if (plant != null) {
            plant.addRating(rating);
        }
    }

    public static void calculatePlantsAverageRating(List<Plant> plants) {

        for (int i = 0; i < plants.size(); i++) {
            Plant plant = plants.get(i);
            plant.calculateAvRating();
        }
    }

    public static void printPlants(List<Plant> plants) {

        Collections.sort(plants,
                (p1,p2) -> {
                    int result = p2.getRarity().compareTo(p1.getRarity());
                    int result2 = p2.getAverageRating().compareTo(p1.getAverageRating());
                    return result == 0 ?
                            result2 : result;
                });
        //Collections.reverse(plants);
        if (!plants.isEmpty()) {
            System.out.println("Plants for the exhibition:");
        }
        for (int i = 0; i < plants.size(); i++) {
            System.out.println(plants.get(i).toString());
        }
    }
}

class Plant {

    private String name;
    private String rarity;
    private List<String> ratings;
    private Double averageRating;

    public Plant(String name, String rarity, List<String> ratings) {
        this.name = name;
        this.rarity = rarity;
        this.ratings = ratings;
        averageRating = 0.0d;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public void removeAllRatings() {
        ratings.clear();
    }

    public void addRating(String rating) {
        ratings.add(rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public List<String> getRatings() {
        return ratings;
    }

    public void setRatings(List<String> ratings) {
        this.ratings = ratings;
    }

    public Double calculateAvRating() {
        double result = 0.0d;
        for (int i = 0; i < ratings.size(); i++) {
            result += Double.parseDouble(ratings.get(i));
        }

        this.averageRating = ratings.size() > 0 ? result/ratings.size() : 0;
        return this.averageRating;
    }

    @Override
    public String toString() {
        return "- "+name+"; Rarity: "+rarity+"; Rating: "+ String.format("%.2f",calculateAvRating());
    }
}
