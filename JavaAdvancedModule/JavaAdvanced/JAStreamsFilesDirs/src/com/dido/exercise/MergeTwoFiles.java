package com.dido.exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MergeTwoFiles {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputOne.txt";

        String pathTwo = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputTwo.txt";

        // Write a program that reads the contents of two text files (inputOne.txt, inputTwo.txt
        // from Resources Exercises)
        // and merges them together into a third one.

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\mergedOutput.txt";


        try {
            PrintWriter writer = new PrintWriter(outputPath);

            try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

                String line = "";

                while((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathTwo))) {

                String line = "";

                while((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.write("\n");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
