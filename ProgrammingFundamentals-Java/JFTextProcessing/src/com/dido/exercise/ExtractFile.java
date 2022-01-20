package com.dido.exercise;

import java.util.Scanner;

public class ExtractFile {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String fileName = sc.nextLine();

        printFileAndExtension(fileName);

    }

    public static void printFileAndExtension(String file) {

        String[] fileParts = file.split("(\\\\)|(\\.)");

        if (fileParts.length >= 2) {
            String name = fileParts[fileParts.length - 2];
            String ext = fileParts[fileParts.length - 1];
            System.out.printf("File name: %s%n", name);
            System.out.printf("File extension: %s%n", ext);
        }
    }
}
