package com.dido.more;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        Map<String, Map<String, DragonStats>> dragonMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String[] dragonData = sc.nextLine().split("\\s+");

            addDragon(dragonMap, dragonData);

        }

        Map<String, DragonAverageStats> dragonAverageMap = new LinkedHashMap<>();
        for (Map.Entry<String, Map<String, DragonStats>> dragonEntry : dragonMap.entrySet()) {

            String type = dragonEntry.getKey();
            Map<String, DragonStats> dragons = dragonEntry.getValue();

            Double averageDamage = dragons.entrySet().stream()
                    .mapToInt(d -> d.getValue().getDamage())
                    .average().getAsDouble();
            Double averageHealth = dragons.entrySet().stream()
                    .mapToInt(d -> d.getValue().getHealth())
                    .average().getAsDouble();

            Double averageArmor = dragons.entrySet().stream()
                    .mapToInt(d -> d.getValue().getArmor())
                    .average().getAsDouble();

            dragonAverageMap.put(type, new DragonAverageStats(averageDamage, averageHealth, averageArmor));
        }

        for (Map.Entry<String, DragonAverageStats> dragonAverageStatsEntry : dragonAverageMap.entrySet()) {

            String type = dragonAverageStatsEntry.getKey();
            DragonAverageStats dragonAverages = dragonAverageStatsEntry.getValue();

            System.out.println(type + "::" + dragonAverages);

            Map<String, DragonStats> dragons = dragonMap.get(type);

            dragons.entrySet().stream()
                    .map(d -> String.format("-%s -> %s", d.getKey(), d.getValue()))
                    .forEach(System.out::println);
        }

    }

    public static void addDragon(Map<String, Map<String, DragonStats>> dragonMap, String[] dragonData) {

        String type = dragonData[0];
        String name = dragonData[1];
        Integer damage;
        if (dragonData[2].equalsIgnoreCase("null")) {
            damage = DragonStats.DAMAGE_DEFAULT;
        } else {
            damage = Integer.parseInt(dragonData[2]);
        }
        Integer health;
        if (dragonData[3].equalsIgnoreCase("null")) {
            health = DragonStats.HEALTH_DEFAULT;
        } else {
            health = Integer.parseInt(dragonData[3]);
        }
        Integer armor;
        if (dragonData[4].equalsIgnoreCase("null")) {
            armor = DragonStats.ARMOR_DEFAULT;
        } else {
            armor = Integer.parseInt(dragonData[4]);
        }


        if (!dragonMap.containsKey(type)) {

            dragonMap.put(type, new TreeMap<>());
        }

        if (!dragonMap.get(type).containsKey(name)) {
            dragonMap.get(type).put(name, new DragonStats(damage, health, armor));
        } else {
            DragonStats dragonStats = dragonMap.get(type).get(name);

            dragonStats.setDamage(damage);
            dragonStats.setHealth(health);
            dragonStats.setArmor(armor);
        }

    }
}

class DragonAverageStats {
    private Double damage;
    private Double health;
    private Double armor;

    public DragonAverageStats(Double damage, Double health, Double armor) {

        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }

    public Double getDamage() {
        return damage;
    }

    public void setDamage(Double damage) {
        this.damage = damage;
    }

    public Double getHealth() {
        return health;
    }

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getArmor() {
        return armor;
    }

    public void setArmor(Double armor) {
        this.armor = armor;
    }

    @Override
    public String toString() {
        return String.format("(%.2f/%.2f/%.2f)", damage, health, armor);
    }
}

class DragonStats {


    public static final Integer DAMAGE_DEFAULT = 45;
    public static final Integer HEALTH_DEFAULT = 250;
    public static final Integer ARMOR_DEFAULT = 10;
    private Integer damage;
    private Integer health;
    private Integer armor;

    public DragonStats(Integer damage, Integer health, Integer armor) {
        //        health 250, damage 45 and armor 10
        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;

    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;

    }

    @Override
    public String toString() {
        return "damage: " + damage + ", health: " + health + ", armor: " + armor;
    }
}