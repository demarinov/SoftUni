package com.dido.exercise;

import java.util.*;

public class HandsOfCards {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, List<String>> cardsMap = new LinkedHashMap<>();
        while(!input.equals("JOKER")) {

            String[] handInfo = input.split(":\\s");
            String person = handInfo[0];
            String[] cardInfo = handInfo[1].split(",\\s");

            cardsMap.putIfAbsent(person, new LinkedList<>());

            List<String> cards = cardsMap.get(person);

            for (int i = 0; i < cardInfo.length; i++) {

                String card = cardInfo[i];

                if (!cards.contains(card)) {
                    cards.add(card);
                }
            }


            input = sc.nextLine();
        }

        Map<String, Integer> cardPower =  new LinkedHashMap<>();
        Map<String, Integer> cardTypePower =  new LinkedHashMap<>();

        populateCardPowers(cardPower, cardTypePower);

        for (String person : cardsMap.keySet()) {

            List<String> cards = cardsMap.get(person);

            int totalPersonCardValue = 0;

            for (int i = 0; i < cards.size(); i++) {

                String cardEntry =  cards.get(i);
                String card = cardEntry.substring(0, cardEntry.length()-1);
                String cardType = cardEntry.substring(cardEntry.length()-1);

                totalPersonCardValue += (cardPower.get(card) * cardTypePower.get(cardType));
            }

            System.out.printf("%s: %d%n",person, totalPersonCardValue);
        }
    }

    public static void populateCardPowers(Map<String, Integer> cardPower,
                                          Map<String, Integer> cardTypePower) {

//        P (2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K, A)
        cardPower.put("2",2);
        cardPower.put("3",3);
        cardPower.put("4",4);
        cardPower.put("5",5);
        cardPower.put("6",6);
        cardPower.put("7",7);
        cardPower.put("8",8);
        cardPower.put("9",9);
        cardPower.put("10",10);
        cardPower.put("J",11);
        cardPower.put("Q",12);
        cardPower.put("K",13);
        cardPower.put("A",14);

//        T (S, H, D, C) (S -> 4, H-> 3, D -> 2, C -> 1).

        cardTypePower.put("S",4);
        cardTypePower.put("H",3);
        cardTypePower.put("D",2);
        cardTypePower.put("C",1);
    }
}
