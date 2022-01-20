package com.dido.exercise;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FakeAdMessage {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> phrase = Arrays.asList("Excellent product.", "Such a great product."
                , "I always use that product.", "Best product of its category."
                , "Exceptional product.", "I can’t live without this product.");

        List<String> events  = Arrays.asList("Now I feel good.", "I have succeeded with this product."
                , "Makes miracles. I am happy of the results!"
                , "I cannot believe but now I feel awesome."
                , "Try it yourself, I am very satisfied.", "I feel great!");

        List<String> authors = Arrays.asList("Diana", "Petya", "Stella"
                , "Elena", "Katya", "Iva", "Annie", "Eva");

        List<String> cities = Arrays.asList("Burgas", "Sofia", "Plovdiv", "Varna", "Ruse");

        int num = Integer.parseInt(sc.nextLine());

        Random rand = new Random();

        for (int i = 0; i < num; i++) {
            int posPhrase = rand.nextInt(phrase.size());
            int posEvent = rand.nextInt(events.size());
            int posAuthor = rand.nextInt(authors.size());
            int posCities = rand.nextInt(cities.size());

            System.out.printf("%s %s %s – %s%n",phrase.get(posPhrase)
                    , events.get(posEvent)
                    , authors.get(posAuthor)
                    , cities.get(posCities));

        }
    }
}
