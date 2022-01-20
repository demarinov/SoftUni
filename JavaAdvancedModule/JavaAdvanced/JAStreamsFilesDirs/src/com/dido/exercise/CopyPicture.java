package com.dido.exercise;

import java.io.*;

public class CopyPicture {

    public static void main(String[] args) {

        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\SamuAvatar.jpg";

        String outputPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\copySamuAvatar.jpg";
        try(FileInputStream reader = new FileInputStream(path)) {

            FileOutputStream writer = new FileOutputStream(outputPath);


            byte[] bytes = new byte[12];

            while(reader.read(bytes) != -1) {

                writer.write(bytes);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
