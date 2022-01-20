package com.dido.lab;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class WordSynonyms {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, ArrayList<String>> words = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String word = sc.nextLine();
            String synonym = sc.nextLine();

            words.putIfAbsent(word, new ArrayList<>());
            words.get(word).add(synonym);
        }

        for (Map.Entry<String, ArrayList<String>> entry: words.entrySet()) {

            System.out.printf("%s - ", entry.getKey());

            for (int i = 0; i < entry.getValue().size(); i++) {

                System.out.printf("%s", entry.getValue().get(i));
                if (i < entry.getValue().size()-1) {
                    System.out.printf(", ");
                }
            }

            System.out.println();
        }
    }
}
