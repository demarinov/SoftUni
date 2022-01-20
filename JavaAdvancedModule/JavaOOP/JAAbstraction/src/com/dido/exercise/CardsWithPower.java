package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class CardsWithPower {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String rankName = sc.nextLine();
        String suitName = sc.nextLine();

        CardRank cardRank = CardRank.getRankByName(rankName);
        CardSuit cardSuit = CardSuit.getSuitByName(suitName);

        int cardPower = cardRank.getValue() + cardSuit.getValue();
        System.out.printf("Card name: %s of %s; Card power: %d",cardRank.getName()
                , cardSuit.getName(),cardPower);
    }

    private enum CardSuit{

        CLUBS("CLUBS",0), DIAMONDS("DIAMONDS",13),
        HEARTS("HEARTS",26), SPADES("SPADES",39);

        private String name;
        private int value;
        CardSuit(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        public static CardSuit getSuitByName(String name) {
            CardSuit cardSuit = Arrays.stream(CardSuit.values())
                    .filter(cs -> name.equals(cs.getName())).findFirst().orElse(null);

            return cardSuit;
        }
    }

    private enum CardRank {

        ACE("ACE",14), TWO("TWO",2), THREE("THREE",3),
        FOUR("FOUR",4), FIVE("FIVE",5), SIX("SIX",6),
        SEVEN("SEVEN",7), EIGHT("EIGHT",8), NINE("NINE",9),
        TEN("TEN",10), JACK("JACK",11), QUEEN("QUEEN",12),
        KING("KING",13);

        private String name;
        private int value;
        CardRank(String name, int value) {

            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        public static CardRank getRankByName(String name) {
            CardRank cardRank = Arrays.stream(CardRank.values())
                    .filter(cr -> name.equals(cr.getName())).findFirst().orElse(null);

            return cardRank;
        }
    }
}
