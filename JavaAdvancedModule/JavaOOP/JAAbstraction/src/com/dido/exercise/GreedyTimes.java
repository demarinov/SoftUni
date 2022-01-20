package com.dido.exercise;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GreedyTimes {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long bagCapacity = Long.parseLong(scanner.nextLine());
        String[] safe = scanner.nextLine().split("\\s+");

        var bag = new LinkedHashMap<String, Map<String, Long>>();

        for (int i = 0; i < safe.length; i += 2) {
            String name = safe[i];
            long quantity = Long.parseLong(safe[i + 1]);

            String item = "";

            if (name.length() == 3) {
                item = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                item = "Gem";
            } else if (name.equalsIgnoreCase("gold")) {
                item = "Gold";
            }

            if (item.equals("")) {
                continue;
            } else if (bagCapacity < bag.values().stream()
                    .map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + quantity) {
                continue;
            }

            switch (item) {
                case "Gem":
                    if (!bag.containsKey(item)) {
                        if (bag.containsKey("Gold")) {
                            if (quantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(item).values().stream()
                            .mapToLong(e -> e).sum() + quantity > bag.get("Gold")
                            .values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!bag.containsKey(item)) {
                        if (bag.containsKey("Gem")) {
                            if (quantity > bag.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (bag.get(item).values().stream().mapToLong(e -> e)
                            .sum() + quantity > bag.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!bag.containsKey(item)) {
                bag.put((item), new LinkedHashMap<>());
            }

            if (!bag.get(item).containsKey(name)) {
                bag.get(item).put(name, 0L);
            }


            bag.get(item).put(name, bag.get(item).get(name) + quantity);
        }

        outputBagContent(bag);
    }

    public static void outputBagContent(Map<String, Map<String, Long>> bag) {
        for (var bagItem : bag.entrySet()) {
            Long sumOfItemValues = bagItem.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", bagItem.getKey(), sumOfItemValues));

            bagItem.getValue().entrySet().stream().sorted((e1, e2) ->
                    e2.getKey().compareTo(e1.getKey()))
                    .forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }

}
