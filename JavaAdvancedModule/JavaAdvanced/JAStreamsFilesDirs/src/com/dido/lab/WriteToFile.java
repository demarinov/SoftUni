package com.dido.lab;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriteToFile {

    public static void main(String[] args) {

        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\output.txt";

        List<Character> punctuationList = new ArrayList<>();
        punctuationList.add('.');
        punctuationList.add(',');
        punctuationList.add('!');
        punctuationList.add('?');

        try (FileInputStream fileInputStream =  new FileInputStream(path)) {

            FileOutputStream fileOutputStream = new FileOutputStream(outputPath);

            int oneByte = 0;

            while((oneByte = fileInputStream.read()) >= 0) {

                if (!punctuationList.contains((char)oneByte)) {
                    fileOutputStream.write(oneByte);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
