package com.dido.exercise;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NetherRealms {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        The input consists of a single line containing all demon names separated
//        by commas and zero or more spaces in the format:
//        "{demon name}, {demon name}, … {demon name}"

        // read/parse the demons and store them into list of names
        // The sum of the asci codes of all characters (excluding numbers (0-9),
        // arithmetic symbols ('+', '-', '*', '/') and delimiter dot ('.')) gives a demon's total health.
        // The sum of all numbers in his name forms his base damage. Note that you should consider the plus '+' and minus '-'
        // signs (e.g. +10 is 10 and -10 is -10).
        // However, there are some symbols ('*' and '/') that can further alter
        // the base damage by multiplying or dividing it by 2 (e.g. in the name "m15*/c-5.0",
        // the base damage is 15 + (-5.0) = 10 and then you need to multiply it by 2 (e.g. 10 * 2 = 20)
        // and then divide it by 2 (e.g. 20 / 2 = 10)).

//        Print all demons sorted by their name in ascending order, each on a separate line in the format:
//•	"{demon name} - {health points} health, {damage points} damage"

        String[] input = sc.nextLine().split(",\\s*");

        String regexHealth = "([^0-9+\\-*\\/.])";
        Pattern patternHealth = Pattern.compile(regexHealth);

        String regexDamage = "([+\\-]?[0-9]+[.]?[0-9]*)";
        Pattern patternDamage = Pattern.compile(regexDamage);

        String regexMulti = "([*\\/])";
        Pattern patternMulti = Pattern.compile(regexMulti);

        Map<String,Demon> demons = new TreeMap<>();
        for (int i = 0; i < input.length; i++) {

            String demonName = input[i].replaceAll("\\s+","");

            // get health
            Matcher matcher = patternHealth.matcher(demonName);
            int totalHealth = 0;
            boolean firstMatch = false;
            while (matcher.find()) {

                char c = matcher.group(1).charAt(0);
                totalHealth += c;
                firstMatch = true;
            }


//            if (!firstMatch) {
//                continue;
//            }
//            System.out.println("Health: "+totalHealth);


            // get damage
            matcher = patternDamage.matcher(demonName);

            double baseDamage = 0.0d;
            while (matcher.find()) {
                // ([*\/]*)([+\-]?[0-9]+[.]?[0-9]*?)([*\/]*)

                String num = matcher.group(1);
                baseDamage += Double.parseDouble(num);

            }

            // add/remove more damage
            matcher = patternMulti.matcher(demonName);

            double totalDamage = baseDamage;
            while(matcher.find()) {

                String ops = matcher.group(0);
                char op = ops.charAt(0);

                switch (op) {
                    case '*':
                        totalDamage *= 2;
                        break;
                    case '/':
                        totalDamage /= 2;
                        break;
                    default:
                        break;
                }
            }


            if (!demonName.isEmpty()) {

                Demon demon = new Demon();
                demon.setName(demonName);
                demon.setHealth(0);
                demon.setDamage(0.0d);
                demons.putIfAbsent(demonName,demon);
                Demon presentDemon = demons.get(demonName);
                presentDemon.setDamage(presentDemon.getDamage()+totalDamage);
                presentDemon.setHealth(presentDemon.getHealth()+totalHealth);
            }
        }


        demons.entrySet().stream()
                .map(d -> d.getValue())
                .forEach(System.out::println);

    }

    static class Demon {

        private String name;
        private Integer health;
        private Double damage;

        public Demon() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getHealth() {
            return health;
        }

        public void setHealth(Integer health) {
            this.health = health;
        }

        public Double getDamage() {
            return damage;
        }

        public void setDamage(Double damage) {
            this.damage = damage;
        }

        @Override
        public String toString() {
            return name + " - " + health + " health, " + String.format("%.2f", damage) + " damage";
        }
    }
}
