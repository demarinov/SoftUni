package com.dido.more;

import java.util.Arrays;
import java.util.Scanner;

public class TreasureFinder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] key = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        String input = sc.nextLine();

        while(!input.equalsIgnoreCase("find")) {

            String text = input;
            StringBuilder decryptedText = new StringBuilder();

            int keyIdx = 0;
            for (int i = 0; i < text.length(); i++) {

                if (keyIdx >= key.length) {
                    keyIdx = 0;
                }

                Character decryptedChar = (char)(text.charAt(i) - key[keyIdx]);
                decryptedText.append(String.format("%c",decryptedChar));

                keyIdx++;
            }

            // parse decryptedText and get type + coordinates of tr.
            getAndPrintTreasure(decryptedText);

            input = sc.nextLine();
        }

    }

    public static void getAndPrintTreasure(StringBuilder text) {

        int startTypeIdx = text.indexOf("&");
        int endTypeIdx = text.lastIndexOf("&");

        String type = "";

        if (startTypeIdx >= 0 && endTypeIdx>= 0) {

            type = text.substring(startTypeIdx+1, endTypeIdx);
        }

        int startCoordinatesIdx = text.indexOf("<");
        int endCoordinatesIdx = text.indexOf(">");

        String coordinates = "";

        if (startCoordinatesIdx >= 0 && endCoordinatesIdx >= 0) {
            coordinates = text.substring(startCoordinatesIdx+1, endCoordinatesIdx);
        }

        if (!type.isEmpty() && !coordinates.isEmpty()) {
            System.out.printf("Found %s at %s%n", type, coordinates);
        }
    }
}
