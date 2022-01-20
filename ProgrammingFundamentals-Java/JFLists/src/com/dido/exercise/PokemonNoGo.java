package com.dido.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PokemonNoGo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> pokemonDist = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> removedElements = new ArrayList<>();

        while(!pokemonDist.isEmpty()) {

            int index = Integer.parseInt(sc.nextLine());
            Integer removedElement;
            if (index < 0) {
                removedElement = pokemonDist.remove(0);
                pokemonDist.add(0, pokemonDist.get(pokemonDist.size()-1));
            } else if (index >= pokemonDist.size()) {
                removedElement = pokemonDist.remove(pokemonDist.size() - 1);
                pokemonDist.add(pokemonDist.get(0));
            } else {

                removedElement = pokemonDist.remove(index);
            }

            removedElements.add(removedElement);

            for (int i = 0; i < pokemonDist.size(); i++) {

                if (removedElement.intValue() >= pokemonDist.get(i).intValue()) {
                    pokemonDist.set(i,pokemonDist.get(i)+removedElement);
                } else {
                    pokemonDist.set(i,pokemonDist.get(i)-removedElement);
                }
            }
        }

        printSumElements(removedElements);
    }

    public static void printSumElements(List<Integer> elems) {
        int sum = 0;

        for (int i = 0; i < elems.size(); i++) {

            sum += elems.get(i);
        }

        System.out.println(sum);
    }
}
