package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordCount {

    public static void main(String[] args) {
        // Write a program that reads a list of words from the file words.txt
        // (from the Resources - Exercises) and finds how many times each of the words
        // is contained in another file text.txt (from the Resources – Exercises).
        // Matching should be case-insensitive.
        //Write the results in file results.txt. Sort the words by frequency in descending order.

        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt";
        String pathTwo = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\countResults.txt";

        Map<String, Integer> wordCount = new LinkedHashMap<>();
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

            String line = "";

            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (int i = 0; i < words.length; i++) {
                    String word = words[i];
                    wordCount.putIfAbsent(word,0);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathTwo))) {

            String line = "";

            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (int i = 0; i < words.length; i++) {

                    String wordTwo = words[i].replaceAll("[,\\.]","");

                    // case insensitive ???
                    if (wordCount.containsKey(wordTwo)) {
                        wordCount.put(wordTwo, wordCount.get(wordTwo)+1);
                    }
                }
            }

            PrintWriter writer = new PrintWriter(outputPath);

            wordCount.entrySet().stream()
                    .sorted((w1,w2) -> w2.getValue().compareTo(w1.getValue()))
                    .forEach(w -> {
                        writer.write(String.format("%s - %d%n",w.getKey(),w.getValue()));
                    });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
