package com.dido.lab;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class SortLines {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        Path inPath = Paths.get(path);
        Path outPath = Paths.get("C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\linesOutput.txt");
        try {
            List<String> lines = Files.readAllLines(inPath);
            Collections.sort(lines);
            Files.write(outPath, lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
