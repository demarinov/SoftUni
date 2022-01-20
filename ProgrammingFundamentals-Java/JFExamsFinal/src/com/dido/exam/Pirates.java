package com.dido.exam;

import java.util.*;

public class Pirates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String command = sc.nextLine();
        List<City> cities = new ArrayList<>();

        while(!command.equalsIgnoreCase("sail")) {

            String[] data = command.split("\\|\\|");

            City city = findCity(cities, data[0]);

            if (city != null) {
                city.addGold(Integer.parseInt(data[2]));
                city.addPopulation(Integer.parseInt(data[1]));
            } else {
                city = new City(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
                cities.add(city);
            }

            command = sc.nextLine();
        }


        command = sc.nextLine();

        while(!command.equalsIgnoreCase("end")) {
//


                String[] commandData = command.split("=>");

                String instruction = commandData[0];

                if (instruction.equalsIgnoreCase("plunder")) {
                    String town = commandData[1];
                    int people = Integer.parseInt(commandData[2]);
                    int gold = Integer.parseInt(commandData[3]);
                    plunderCity(cities, town, people, gold);
                } else if (instruction.equalsIgnoreCase("prosper")) {
                    String town = commandData[1];
                    int gold = Integer.parseInt(commandData[2]);
                    prosperCity(cities, town, gold);
                }

                command = sc.nextLine();

        }

        //System.out.println(cities.toString().replaceAll("[\\[\\],]",""));
        printCities(cities);

    }

    public static void printCities(List<City> cities) {

        if (cities.size() > 0) {

            Collections.sort(cities,(c1,c2) -> {

                int result = c2.getGold().compareTo(c1.getGold());

                return result == 0 ? c1.getName().compareTo(c2.getName()) : result;

            });
            System.out.println("Ahoy, Captain! There are "+cities.size()+" wealthy settlements to go to:");
            for (int i = 0; i < cities.size(); i++) {
                System.out.println(cities.get(i));
            }
        } else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");
        }
    }

    public static void prosperCity(List<City> cities, String town, int gold) {
        //•	"Prosper=>{town}=>{gold}"
//                o	There has been a dramatic economic growth in the given city,
//                increasing its treasury by the given amount of gold.
//                o	The gold amount can be a negative number, so be careful.
//                If a negative amount of gold is given print:
//                "Gold added cannot be a negative number!" and ignore the command.
//                o	If the given gold is a valid amount, increase the town's gold reserves
//                by the respective amount and print the following message:
//                "{gold added} gold added to the city treasury. {town} now has {total gold} gold."


        if (gold < 0) {
            System.out.println("Gold added cannot be a negative number!");
            return;
        }

        City city = findCity(cities, town);

        if (city != null) {
            city.addGold(gold);
            System.out.println(gold+" gold added to the city treasury. "+town+" now has "+city.getGold()+" gold.");
        }

    }

    public static void plunderCity(List<City> cities, String town, int people, int gold) {
        //            •	"Plunder=>{town}=>{people}=>{gold}"
//            o	You have successfully attacked and plundered the town,
//            killing the given number of people and stealing the respective amount of gold.
//            o	For every town you attack print this message: "{town} plundered!
//            {gold} gold stolen, {people} citizens killed."
//            o	If any of those two values (population or gold) reaches zero, the town is disbanded.
//	You need to remove it from your collection of targeted cities and print
// the following message: "{town} has been wiped off the map!"
//            o	There will be no case of receiving more people or gold than there is in the city.

        City city = findCity(cities, town);

        if (city != null) {
            city.decreasePopulation(people);
            city.decreaseGold(gold);

            System.out.println(town+" plundered! " +
                    gold+" gold stolen, "+people+" citizens killed.");

            if (city.getPopulation() == 0 || city.getGold() == 0) {
                cities.remove(city);
                System.out.println(town+" has been wiped off the map!");
            }
        }




    }

    public static City findCity(List<City> cities, String cityName) {

        City city = cities.stream().filter(c -> c.getName().equals(cityName)).findFirst().orElse(null);

        return city;
    }
}

class City {

    private String name;
    private Integer population;
    private Integer gold;

    public City(String name, Integer population, Integer gold) {
        this.name = name;
        this.population = population;
        this.gold = gold;
    }

    public void addGold(int gold) {

        this.gold += gold;
    }

    public void decreaseGold(int gold) {

        this.gold -= gold;
    }

    public void addPopulation(int population) {

        this.population += population;
    }

    public void decreasePopulation(int population) {

        this.population -= population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getGold() {
        return gold;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    @Override
    public String toString() {
        return name+" -> Population: "+population+" citizens, Gold: "+gold+" kg";
    }
}