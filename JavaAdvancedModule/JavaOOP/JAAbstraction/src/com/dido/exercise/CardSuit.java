package com.dido.exercise;

import java.util.Scanner;

public class CardSuit {

    public static void main(String[] args) {

        System.out.println("Card Suits:");
        for (CardSuits cardSuit: CardSuits.values()) {

            System.out.printf("Ordinal value: %d; Name value: %s%n",cardSuit.ordinal(),cardSuit.getValue());
        }
    }

    private enum CardSuits {
        Clubs("Clubs"), Diamonds("Diamonds")
        ,Hearts("Hearts"), Spades("Spades");

        private String value;
        CardSuits(String name) {
            this.value = name;
        }

        public String getValue() {
            return value;
        }
    }
}
