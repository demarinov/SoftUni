package com.dido.exercise;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StarEnigma {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

//        The planet name starts after '@' and contains only letters from the Latin alphabet.
//        The planet population starts after ':' and is an Integer;
//        The attack type may be "A"(attack) or "D"(destruction) and must be surrounded by
//        "!" (exclamation mark).
//        The soldier count starts after "->" and should be an Integer.
//        The order in the message should be: planet name ->
//        planet population -> attack type -> soldier count.
//        Each part can be separated from the others by any character except:
//        '@', '-', '!', ':' and '>'.
        String regex = "[^@\\-!:]*?@([A-Za-z]+)[^@\\-!:]*?:([0-9]+)[^@\\-!:]*?!([AD])![^@\\-!:]*?->([0-9]+)";
        Pattern pattern = Pattern.compile(regex);

        List<Planet> planets = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();

            String decrypted = decrypt(input);

            List<Planet> planetList = parsePlanets(decrypted, pattern);
            planets.addAll(planetList);
        }


//        "Attacked planets: {attackedPlanetsCount}"
//        "-> {planetName}"
//        "Destroyed planets: {destroyedPlanetsCount}"
//        "-> {planetName}"
//        The planets should be ordered by name alphabetically.


        long attackedCount = planets.stream().filter(p -> p.getType().equalsIgnoreCase("a"))
                .count();

        long destroyedCount = planets.stream().filter(p -> p.getType().equalsIgnoreCase("d"))
                .count();

        System.out.printf("Attacked planets: %d%n",attackedCount);

        planets.stream().sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                .filter(p -> p.getType().equalsIgnoreCase("a"))
                .map(p -> String.format("-> %s",p.getName()))
                .forEach(System.out::println);

        System.out.printf("Destroyed planets: %d%n",destroyedCount);
        planets.stream().sorted((p1,p2) -> p1.getName().compareTo(p2.getName()))
                .filter(p -> p.getType().equalsIgnoreCase("d"))
                .map(p -> String.format("-> %s",p.getName()))
                .forEach(System.out::println);
    }

    static class Planet {

        private String name;
        private String population;
        private String type;
        private String count;

        public Planet() {
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPopulation() {
            return population;
        }

        public void setPopulation(String population) {
            this.population = population;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }

    public static List<Planet> parsePlanets(String decrypted, Pattern pattern) {

        Matcher matcher = pattern.matcher(decrypted);

        List<Planet> planets = new LinkedList<>();

        while(matcher.find()) {

            String name = matcher.group(1);
            String population = matcher.group(2);
            String type = matcher.group(3);
            String count = matcher.group(4);

            Planet planet = new Planet();
            planet.setName(name);
            planet.setPopulation(population);
            planet.setType(type);
            planet.setCount(count);

            planets.add(planet);
        }

        return planets;

    }

    public static String decrypt(String input) {

        // [s, t, a, r] count

        String inputLowerCase = input.toLowerCase();
        int count = 0;

        for (int i = 0; i < inputLowerCase.length(); i++) {

            char c = inputLowerCase.charAt(i);

            if (c == 's' || c == 't' || c == 'a' || c == 'r') {
                count++;
            }
        }

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);

            char newChar =(char) (c - count);

            decryptedText.append(String.format("%c",newChar));
        }

        return decryptedText.toString();
    }
}
