package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AllCapitals {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\capitalsOutput.txt";

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {

            PrintWriter writer = new PrintWriter(outputPath);

            String line = "";

            while((line = reader.readLine()) != null) {

                writer.write(line.toUpperCase());
                writer.write('\n');
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
