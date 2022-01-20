package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DragonArmy {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // {type} {name} {damage} {health} {armor}.

        // health 250, damage 45, and armor 10.

        int n = Integer.parseInt(sc.nextLine());

        Map<String, Map<String, DragonStat>> dragonMap = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {

            String[] dragonData = sc.nextLine().split("\\s");

            String type = dragonData[0];
            String name = dragonData[1];

            Integer health = 250;
            Integer damage = 45;
            Integer armor = 10;

            if (!dragonData[3].equals("null")) {
                health = Integer.parseInt(dragonData[3]);
            }

            if (!dragonData[2].equals("null")) {
                damage = Integer.parseInt(dragonData[2]);
            }

            if (!dragonData[4].equals("null")) {
                armor = Integer.parseInt(dragonData[4]);
            }

            dragonMap.putIfAbsent(type, new TreeMap<>());

            Map<String, DragonStat> dragonStatMap = dragonMap.get(type);
            dragonStatMap.put(name, new DragonStat(health, damage, armor));

        }

        // {Type}::({damage}/{health}/{armor})
        // {Name} -> damage: {damage}, health: {health}, armor: {armor}

        dragonMap.entrySet().stream()
                .map(d ->
                {
                    String type = d.getKey();
                    Double avHealth = d.getValue().entrySet().stream()
                            .mapToInt(e -> e.getValue().getHealth())
                            .average().getAsDouble();
                    Double avDamage = d.getValue().entrySet().stream()
                            .mapToInt(e -> e.getValue().getDamage())
                            .average().getAsDouble();
                    Double avArmor = d.getValue().entrySet().stream()
                            .mapToInt(e -> e.getValue().getArmor())
                            .average().getAsDouble();

                    String dragonValues = d.getValue().entrySet().stream()
                            .map(ds ->
                            {
                                String name = ds.getKey();
                                Integer health = ds.getValue().getHealth();
                                Integer damage = ds.getValue().getDamage();
                                Integer armor = ds.getValue().getArmor();

                                return String.format("-%s -> damage: %d, " +
                                        "health: %d, armor: %d%n",name, damage, health, armor);
                            })
                            .reduce("",String::concat);

                    return String.format("%s::(%.2f/%.2f/%.2f)%n%s",type,
                            avDamage, avHealth, avArmor, dragonValues);
                })
                .forEach(System.out::print);
    }

    static class DragonStat {

        private Integer health;
        private Integer damage;
        private Integer armor;

        public DragonStat() {
        }

        public DragonStat(Integer health, Integer damage, Integer armor) {
            this.health = health;
            this.damage = damage;
            this.armor = armor;
        }

        public Integer getHealth() {
            return health;
        }

        public void setHealth(Integer health) {
            this.health = health;
        }

        public Integer getDamage() {
            return damage;
        }

        public void setDamage(Integer damage) {
            this.damage = damage;
        }

        public Integer getArmor() {
            return armor;
        }

        public void setArmor(Integer armor) {
            this.armor = armor;
        }
    }
}
