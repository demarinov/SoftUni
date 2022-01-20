package com.dido.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Maino {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] elements  = reader.readLine().substring(10).split(",\\s");
        int[] universe = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(reader.readLine().substring(16));

        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {

            String[] setElements = reader.readLine().split(",\\s");
            int[] set =new int[setElements.length];

            for (int j = 0; j < setElements.length; j++) {
                set[j] = Integer.parseInt(setElements[j]);
            }

            sets.add(set);
        }

        List<int[]> chosenSets = chosenSet(sets, universe);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Sets to take (%d):%n",chosenSets.size()));

        for (int[] set : chosenSets) {
            stringBuilder.append("{ ");
            stringBuilder.append(Arrays.toString(set).replaceAll("\\[|]",""));
            stringBuilder.append(" }").append(System.lineSeparator());
        }

        System.out.println(stringBuilder);
    }

    public static List<int[]> chosenSet(List<int[]> sets, int[] universe) {

        List<int[]> selectedSets = new ArrayList<>();

        List<Integer> universeSet = Arrays.stream(universe).mapToObj(s -> s)
                .collect(Collectors.toList());
        while(!universeSet.isEmpty()) {

            int notChosenCount = 0;
            int[] chosenSet = sets.get(0);
            for (int[] set:sets) {
                int count = 0;
                for (int elem : set) {
                    if (universeSet.contains(elem)) {
                        count++;
                    }
                }

                if (notChosenCount < count) {
                    notChosenCount = count;
                    chosenSet = set;
                }
            }

            selectedSets.add(chosenSet);
            for (int i = 0; i < chosenSet.length; i++) {
                universeSet.remove((Integer) chosenSet[i]);
            }

        }


        return selectedSets;
    }
}
