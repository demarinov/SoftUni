package com.dido.lab;

import java.io.*;

public class CopyBytes {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        String outPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\asciiOutput.txt";

        try (FileInputStream fileInputStream = new FileInputStream(path)) {

            FileOutputStream fileOutputStream = new FileOutputStream(outPath);

            int oneByte = 0;

            while ((oneByte = fileInputStream.read()) >= 0) {


                if (oneByte == 10 || oneByte == 32) {
                    fileOutputStream.write(oneByte);
                    continue;
                }
                String digits = String.valueOf(oneByte);

                for (int i = 0; i < digits.length(); i++) {

                    fileOutputStream.write(digits.charAt(i));

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
