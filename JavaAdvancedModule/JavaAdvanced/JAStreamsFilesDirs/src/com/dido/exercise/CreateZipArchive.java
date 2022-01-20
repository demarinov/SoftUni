package com.dido.exercise;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipArchive {

    public static void main(String[] args) throws IOException {

        // Write a program that reads three .txt files and creates a zip archive named files.zip.
        // Use FileOutputStream, ZipOutputStream, and FileInputStream.

        String pathOne = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\file1.txt";

        String pathTwo = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\file2.txt";

        String pathThree = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\file3.txt";

        String zipPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\files.zip";

        List<String> srcFiles = Arrays.asList(pathOne, pathTwo, pathThree);

        FileOutputStream outputStream = new FileOutputStream(zipPath);
        ZipOutputStream zipOut = new ZipOutputStream(outputStream);

        for (String srcFile : srcFiles) {

            File fileToZip = new File(srcFile);
            FileInputStream inputStream = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
            zipOut.putNextEntry(zipEntry);

            byte[] bytes = new byte[1024];

            int length;
            while((length = inputStream.read(bytes)) >=0) {
                zipOut.write(bytes,0, length);
            }

            inputStream.close();
        }

        zipOut.close();
        outputStream.close();
    }
}
