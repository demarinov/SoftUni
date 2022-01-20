package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> firstDeck = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());
        List<Integer> secondDeck = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int i = 0;
        int j = 0;
        while(!firstDeck.isEmpty() && !secondDeck.isEmpty()) {

            if(firstDeck.get(0).compareTo(secondDeck.get(0)) > 0) {
                Integer elemOne = firstDeck.get(0);
                firstDeck.remove(0);
                Integer elemTwo = secondDeck.get(0);
                secondDeck.remove(0);

                firstDeck.add(elemOne);
                firstDeck.add(elemTwo);
            } else if(firstDeck.get(0).compareTo(secondDeck.get(0)) < 0) {
                Integer elemOne = firstDeck.get(0);
                firstDeck.remove(0);
                Integer elemTwo = secondDeck.get(0);
                secondDeck.remove(0);

                secondDeck.add(elemTwo);
                secondDeck.add(elemOne);

            } else {
                firstDeck.remove(0);
                secondDeck.remove(0);
            }

        }

        if (firstDeck.isEmpty()) {
            // second pl wins
            printSum(secondDeck, 2);
        } else {
            // first pl wins
            printSum(firstDeck, 1);
        }

    }

    public static void printSum(List<Integer> deck, int winner) {
        int sum = 0;

        for (int i = 0; i < deck.size(); i++) {
            //System.out.printf("%d ",deck.get(i));
            sum +=deck.get(i);
        }

        if (winner == 1) {
            System.out.printf("First player wins! Sum: %d",sum);
        } else {
            System.out.printf("Second player wins! Sum: %d",sum);
        }
    }
}
