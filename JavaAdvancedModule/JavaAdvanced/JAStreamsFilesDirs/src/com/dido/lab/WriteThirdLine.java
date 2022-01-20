package com.dido.lab;

import java.io.*;
import java.util.Scanner;

public class WriteThirdLine {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\thirdLineOutput.txt";
        PrintWriter writer = new PrintWriter(new FileWriter(outputPath));

        String line = "";
        int countLines = 0;
        while(reader.ready()) {

            line = reader.readLine();
            countLines++;
            if (countLines % 3 == 0) {
                writer.println(line);
            }

        }

        reader.close();
        writer.close();
    }
}
