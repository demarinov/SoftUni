package com.dido.more;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SnowWhite {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, Integer> dwarfHatMap = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("once upon a time")) {

            String[] dwarfData = input.split(" <:> ");

            addDwarf(dwarfHatMap, dwarfData);

            input = sc.nextLine();
        }

        dwarfHatMap.entrySet().stream()
                .sorted((d1, d2) -> {

                    int result = d2.getValue().compareTo(d1.getValue());

                    if (result == 0) {

                        // parse hat color from combined key
                        String hatColorOne = d1.getKey().split(":")[0];
                        String hatColorTwo = d2.getKey().split(":")[0];

                        Long countOne = dwarfHatMap.entrySet().stream()
                                .filter(d -> d.getKey().contains(hatColorOne)).count();
                        Long countTwo = dwarfHatMap.entrySet().stream()
                                .filter(d -> d.getKey().contains(hatColorTwo)).count();

                        return countTwo.compareTo(countOne);
                    }

                    return result;


                })
                .map(d -> String.format("(%s) %s <-> %d",d.getKey().split(":")[0],
                        d.getKey().split(":")[1], d.getValue()))
                .forEach(System.out::println);
    }

    public static void addDwarf(Map<String, Integer> dwarfHatMap, String[] dwarfData) {
        String dwarfName = dwarfData[0];
        String dwarfHatColor = dwarfData[1];
        String combinedKey = dwarfHatColor + ":" + dwarfName;
        Integer dwarfPhysics = Integer.parseInt(dwarfData[2]);

        if (!dwarfHatMap.containsKey(combinedKey)) {

            dwarfHatMap.put(combinedKey, dwarfPhysics);
        }

        if (dwarfHatMap.get(combinedKey) < dwarfPhysics) {

            dwarfHatMap.put(combinedKey, dwarfPhysics);
        }

    }
}
