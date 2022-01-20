package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LineNumbers {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\lineNumbersOutput.txt";

        try(BufferedReader reade = Files.newBufferedReader(Paths.get(path))) {

            PrintWriter writer = new PrintWriter(outputPath);

            String line="";
            int count = 0;
            while((line = reade.readLine()) != null) {

                count++;
                writer.write(String.format("%d. %s%n",count,line));
            }


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
