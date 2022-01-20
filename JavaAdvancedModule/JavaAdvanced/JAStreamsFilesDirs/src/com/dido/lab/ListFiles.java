package com.dido.lab;

import java.io.File;
import java.util.Arrays;

public class ListFiles {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\deyan\\Documents\\SoftUni" +
                "\\JavaAdvanced\\May20\\04. Java-Advanced-Files-and-Streams-Lab-Resources" +
                "\\Files-and-Streams");
        File[] files = file.listFiles();

        Arrays.stream(files)
                .filter(f -> f.isFile())
                .map(f -> {

                    return String.format("%s: [%d]",f.getName(),f.length());
                })
                .forEach(System.out::println);

    }
}
