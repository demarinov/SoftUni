package com.dido.exercise;

import java.io.File;

public class GetFolderSize {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";
        // Write a program that traverses a folder and calculates its size in bytes.
        // Use Folder Exercises Resources in Resources.

        File dir = new File(path);

        File[] files = dir.listFiles();

        long count = 0;
        for (File file: files) {

            count += file.length();
        }

        System.out.printf("Folder size: %d",count);
    }
}
