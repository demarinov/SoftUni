package com.dido.exercise;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LegendaryFarming {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Map<String, Integer> keyMaterials =  new TreeMap<>();
        keyMaterials.putIfAbsent("shards",0);
        keyMaterials.putIfAbsent("fragments",0);
        keyMaterials.putIfAbsent("motes",0);
        Map<String, Integer> junkMaterials =  new TreeMap<>();

        boolean winnerFound = false;
        String winningMaterial = "";
        while(true) {

            String input = sc.nextLine();

            String[] materials = input.split("\\s");

            for (int i = 0; i < materials.length; i+=2) {

                String material = materials[i+1].toLowerCase();
                Integer qty = Integer.parseInt(materials[i]);

                boolean isWinner = checkMaterial(material, qty, keyMaterials, junkMaterials);

                if (isWinner) {
                    winningMaterial = material;
                    winnerFound = true;
                    break;
                }

            }

            if (winnerFound) {

                switch(winningMaterial) {

                    case "shards":
                        System.out.println("Shadowmourne obtained!");
                        break;
                    case "fragments":
                        System.out.println("Valanyr obtained!");
                        break;
                    case "motes":
                        System.out.println("Dragonwrath obtained!");
                        break;
                    default:
                        break;
                }

                break;
            }

        }

        // print remaining key materials in desc order
        keyMaterials.entrySet().stream()
                .sorted((m1,m2) -> m2.getValue().compareTo(m1.getValue()))
                .forEach(m -> System.out.printf("%s: %d%n",m.getKey(), m.getValue()));

        junkMaterials.entrySet().stream().forEach(j -> System.out.printf("%s: %d%n",j.getKey(), j.getValue()));

    }

    public static boolean checkMaterial(String material, Integer qty,
                                     Map<String,Integer> keyMaterial,
                                     Map<String, Integer> junkMaterial) {

//•	Shadowmourne – requires 250 Shards;
//•	Valanyr – requires 250 Fragments;
//•	Dragonwrath – requires 250 Motes;

        switch(material) {

            case "shards":
            case "fragments":
            case "motes":
                keyMaterial.put(material, keyMaterial.get(material)+qty);

                if (keyMaterial.get(material) >= 250) {
                    keyMaterial.put(material, keyMaterial.get(material)-250);
                    return true;
                }

                return false;
            default:
                junkMaterial.putIfAbsent(material,0);
                junkMaterial.put(material, junkMaterial.get(material)+qty);

                return false;
        }
    }
}
