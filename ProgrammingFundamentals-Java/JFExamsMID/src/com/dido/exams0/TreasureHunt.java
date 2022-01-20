package com.dido.exams0;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TreasureHunt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> lootChest = Arrays.stream(sc.nextLine().split("\\|"))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        while(!input.equals("Yohoho!")) {

            String[] commandData = input.split("\\s");

            switch(commandData[0]) {

                case "Loot":
                    for (int i = 1; i < commandData.length; i++) {
                        String item = commandData[i];

                        if (!lootChest.contains(item)) {
                            lootChest.add(0, item);
                        }
                    }

                    break;
                case "Drop":
                    int index = Integer.parseInt(commandData[1]);

                    if (index < 0 || index >= lootChest.size()) {
                        input = sc.nextLine();
                        continue;
                    }

                    String lootItem = lootChest.remove(index);
                    lootChest.add(lootItem);
                    break;
                case "Steal":
                    int countToSteal = Integer.parseInt(commandData[1]);
                    List<String> stolenLoot = new LinkedList<>();

                    if (countToSteal > lootChest.size()) {
                        for (int i = 0; i < lootChest.size(); i++) {
                            stolenLoot.add(lootChest.remove(i));
                            i--;
                        }
                    } else {

                        for (int i = 0; i < countToSteal; i++) {
                            stolenLoot.add(0,lootChest.remove(lootChest.size()-1));
                        }
                    }

                    System.out.println(stolenLoot.toString().replaceAll("[\\[\\]]",""));
                    break;

                default:
                    break;

            }

            input = sc.nextLine();
        }



        if (lootChest.isEmpty()) {
            System.out.printf("Failed treasure hunt.");
        } else {

            int sumLen = lootChest.stream().map(s -> s.length())
                    .mapToInt(d -> d)
                    .sum();
            double averageTreasureGain = 1.0 * sumLen / lootChest.size();
            System.out.printf("Average treasure gain: %.2f pirate credits.",averageTreasureGain);
        }
    }
}
