package com.dido.exercise;


public class CardRank {

    public static void main(String[] args) {


        System.out.println("Card Ranks:");
        for (CardRanks cardRank : CardRanks.values()) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",cardRank.ordinal(), cardRank.getRank());
        }

    }

    private enum CardRanks {

        ACE("Ace"), TWO("Two"), THREE("Three"),
        FOUR("Four"), FIVE("Five"), SIX("Six"), SEVEN("Seven"), EIGHT("Eight"),
        NINE("Nine"), TEN("Ten"), JACK("Jack"), QUEEN("Queen"), KING("King");

        private String rank;
        CardRanks(String rank) {
            this.rank = rank;
        }


        public String getRank() {
            return rank;
        }
    }
}
