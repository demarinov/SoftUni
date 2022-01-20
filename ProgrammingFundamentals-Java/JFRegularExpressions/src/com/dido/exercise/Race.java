package com.dido.exercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Race {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = Arrays.asList(sc.nextLine().split(", "));

        // G4e@55or%6g6!68e!!@
        String regex = "([A-Za-z0-9]?)";
        Pattern pattern = Pattern.compile(regex);

        String input = sc.nextLine();

        Map<String, Integer> racers = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("end of race")) {

            Matcher matcher = pattern.matcher(input);

            StringBuilder name = new StringBuilder();
            int distance = 0;
            while(matcher.find()) {

                String character = matcher.group(1);

                if (character == null || character.isEmpty()) {
                    continue;
                }

                if (Character.isAlphabetic(character.charAt(0))) {
                   name.append(character);
                }


                if (Character.isDigit(character.charAt(0))) {
                    distance += Integer.parseInt(character);
                }
            }


            if (names.contains(name.toString())) {
                racers.putIfAbsent(name.toString(), 0);
                racers.put(name.toString(), racers.get(name.toString())+distance);
            }

            input = sc.nextLine();
        }

        List<Map.Entry<String, Integer>> finalRacers = racers.entrySet().stream().sorted((r1,r2) -> r2.getValue().compareTo(r1.getValue()))
                .limit(3).collect(Collectors.toList());

        int count = 1;
        for (Map.Entry<String, Integer> racer : finalRacers) {

            if (count==1) {
                System.out.printf("%dst place: %s%n", count++, racer.getKey());
            } else if (count == 2) {
                System.out.printf("%dnd place: %s%n", count++, racer.getKey());
            } else {
                System.out.printf("%drd place: %s%n", count++, racer.getKey());
            }
        }
    }
}
