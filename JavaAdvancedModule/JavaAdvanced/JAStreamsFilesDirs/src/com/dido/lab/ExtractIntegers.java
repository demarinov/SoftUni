package com.dido.lab;

import java.io.*;
import java.util.Scanner;

public class ExtractIntegers {

    public static void main(String[] args) {
        String path = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        String outPath = "C:\\Users\\deyan\\Documents\\SoftUni\\JavaAdvanced\\May20" +
                "\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\intOutput.txt";

        try (Scanner sc = new Scanner(new FileInputStream(path))) {

            PrintWriter out = new PrintWriter(new FileOutputStream(outPath));

            while(sc.hasNext()) {

                if (sc.hasNextInt()) {
                    out.println(sc.nextInt());
                }

                sc.next();
            }

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
