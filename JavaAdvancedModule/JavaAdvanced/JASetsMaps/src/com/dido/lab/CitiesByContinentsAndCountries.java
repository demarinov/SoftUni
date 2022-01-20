package com.dido.lab;

import java.util.*;

public class CitiesByContinentsAndCountries {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        Write a program to read continents, countries and their cities,
//                put them in a nested map and print them in the order of first appearance.

        int count = Integer.parseInt(sc.nextLine());

        Map<String, Map<String, List<String>>> continentsMap = new LinkedHashMap<>();
        for (int i = 0; i < count; i++) {

            String[] continentData = sc.nextLine().split("\\s");
            String continent = continentData[0];
            String country = continentData[1];
            String city = continentData[2];

            if (!continentsMap.containsKey(continent)) {

                Map<String, List<String>> countries = new LinkedHashMap<>();
                List<String> cities = new LinkedList<>();
                cities.add(city);
                countries.put(country, cities);
                continentsMap.put(continent, countries);

            } else {

                Map<String, List<String>> countries = continentsMap.get(continent);

                if (countries.containsKey(country)) {

                    List<String> cities = countries.get(country);
                    cities.add(city);
                } else {
                    List<String> cities = new LinkedList<>();
                    cities.add(city);
                    countries.put(country, cities);
                }

            }

        }

        continentsMap.entrySet().stream()
                .map(ce ->{

                    String continent = ce.getKey();
                    Map<String, List<String>> countries = ce.getValue();

                    String countryValues = countries.entrySet().stream()
                            .map(c -> {

                                  String country = c.getKey();

                                  String citiesValue = c.getValue().stream()
                                          .map(ci -> String.format("%s, ",ci))
                                          .reduce("",String::concat);

                                  // remove the last comma and space
                                  return String.format("  %s -> %s%n",country,
                                          citiesValue.substring(0, citiesValue.length()-2));
                            })
                            .reduce("",String::concat);

                    return String.format("%s:%n%s", continent, countryValues);

                })
                .forEach(System.out::print);

    }
}
