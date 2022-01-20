package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PopulationCounter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, Map<String, Integer>> populationMap = new LinkedHashMap<>();
        while(!input.equals("report")) {

            String[] countryData = input.split("\\|");
            String city = countryData[0];
            String country = countryData[1];
            Integer population = Integer.parseInt(countryData[2]);

            populationMap.putIfAbsent(country,new LinkedHashMap<>());
            Map<String, Integer> cityPopulationMap = populationMap.get(country);
            cityPopulationMap.putIfAbsent(city, population);

            input = sc.nextLine();
        }

        populationMap.entrySet().stream()
                .sorted((e1,e2) -> {

                    Long totalPopulationE1 = e1.getValue().entrySet().stream()
                            .mapToLong(s -> s.getValue())
                            .sum();

                    Long totalPopulationE2 = e2.getValue().entrySet().stream()
                            .mapToLong(s -> s.getValue())
                            .sum();

                    return totalPopulationE2.compareTo(totalPopulationE1);
                })
                .map(e -> {

                    String country = e.getKey();
                    Long totalPopulation = e.getValue().entrySet().stream()
                            .mapToLong(s -> s.getValue())
                            .sum();
                    String countryValues = String.format("%s (total population: %d)",
                            country,totalPopulation);

                    String cityValues = e.getValue().entrySet().stream()
                            .sorted((c1,c2) -> {

                                return c2.getValue().compareTo(c1.getValue());
                            })
                            .map(c ->{

                                return String.format("=>%s: %d%n",c.getKey(), c.getValue());
                            })
                            .reduce("",String::concat);

                    return String.format("%s%n%s",countryValues,cityValues);
                })
                .forEach(System.out::print);

    }
}
