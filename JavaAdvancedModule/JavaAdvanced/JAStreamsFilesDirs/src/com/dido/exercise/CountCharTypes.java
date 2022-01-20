package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CountCharTypes {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\typeCountOutput.txt";

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

            PrintWriter writer = new PrintWriter(outputPath);

            String line = "";

            List<Integer> charCount = new ArrayList<>();
            charCount.add(0);
            charCount.add(0);
            charCount.add(0);
            while((line = reader.readLine()) != null) {


                countCharTypes(line, charCount);
            }

            writer.write(String.format("Vowels: %d%n",charCount.get(0)));
            writer.write(String.format("Consonants: %d%n",charCount.get(1)));
            writer.write(String.format("Punctuation: %d%n",charCount.get(2)));

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void countCharTypes(String line, List<Integer> charCount) {

        // 0 - vowels, 1 - consonants, 2 - punctuations
        for (int i = 0; i < line.length(); i++) {

            Character c = line.charAt(i);

            if (c == 'a' || c == 'e' || c== 'o'
                    || c == 'i' || c == 'u') {
                charCount.set(0,charCount.get(0)+1);
            } else if (c == ',' || c =='.'
                    || c == '!' || c == '?') {
                charCount.set(2,charCount.get(2)+1);
            } else if (c != '\n' && c != '\t'
                    && c != ' ' && c != '\r') {
                charCount.set(1,charCount.get(1)+1);
            }
        }
    }
}
