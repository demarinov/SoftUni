package com.dido.exercise;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class LegendaryFarming {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> keyMaterials = new LinkedHashMap<>();
        initKeyMaterials(keyMaterials);
        Map<String, Integer> junkMaterials = new LinkedHashMap<>();
        Map<String, String> mapMaterialsToItems = new HashMap<>();
        mapMaterialsToItems.put("Shadowmourne","250 shards");
        mapMaterialsToItems.put("Valanyr","250 fragments");
        mapMaterialsToItems.put("Dragonwrath","250 motes");
        String input = sc.nextLine();

        String legendaryItem = "";
        for (;;) {

            String[] materialsData = input.split("\\s+");
            legendaryItem = extractMaterialsAndLegendary(materialsData, keyMaterials,
                    junkMaterials,mapMaterialsToItems);

            if (!legendaryItem.isEmpty()) {
                break;
            }

            input = sc.nextLine();
        }


        // Output legendary and materials
        System.out.println(legendaryItem+" obtained!");
        keyMaterials.entrySet().stream()
                .sorted((e1,e2) -> {

                    int result = e2.getValue().compareTo(e1.getValue());

                    return result == 0 ? e1.getKey().compareTo(e2.getKey()) :result;

                })
                .map(e -> String.format("%s: %d",e.getKey(), e.getValue()))
                .forEach(System.out::println);
        junkMaterials.entrySet().stream()
                .sorted((e1,e2) -> e1.getKey().compareTo(e2.getKey()))
                .map(e -> String.format("%s: %d",e.getKey(), e.getValue()))
                .forEach(System.out::println);

    }

    public static void initKeyMaterials(Map<String, Integer> keyMaterials) {

        keyMaterials.putIfAbsent("shards",0);
        keyMaterials.putIfAbsent("motes",0);
        keyMaterials.putIfAbsent("fragments",0);
    }

    public static String extractMaterialsAndLegendary(String[] materialsData,
                                                      Map<String, Integer> keyMaterials,
                                                      Map<String, Integer> junkMaterials,
                                                      Map<String, String> mapMaterialsToItems) {

        String legendaryItem = "";
        for (int i = 0; i < materialsData.length; i+=2) {
            Integer countMaterial = Integer.parseInt(materialsData[i]);
            String material = materialsData[i+1].toLowerCase();

            switch (material) {

                case "shards":
                case "motes":
                case "fragments":
                    Integer countItem = keyMaterials.get(material)+countMaterial;
                    keyMaterials.put(material,countItem);

                    if (countItem >= 250) {

                        legendaryItem = mapMaterialsToItems.entrySet().stream()
                                .filter(e -> e.getValue().contains(material))
                                .map(e -> e.getKey()).collect(Collectors.joining());
                        keyMaterials.put(material,keyMaterials.get(material) - 250);
                        return legendaryItem;
                    }
                    break;
                default:
                    junkMaterials.putIfAbsent(material, 0);
                    junkMaterials.put(material,junkMaterials.get(material)+countMaterial);
                    break;
            }
        }

        return legendaryItem;

    }
}
