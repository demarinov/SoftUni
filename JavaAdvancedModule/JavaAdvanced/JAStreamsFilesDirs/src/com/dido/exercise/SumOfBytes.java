package com.dido.exercise;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumOfBytes {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {


            String line = reader.readLine();
            int sum = 0;
            while(line != null) {

                for(char c : line.toCharArray()) {
                    sum += c;
                }

                line = reader.readLine();;
            }


            System.out.printf("%d%n",sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
