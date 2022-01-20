package com.company.more;

import java.util.Scanner;

public class WweddingSeats {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char lastSector = sc.nextLine().charAt(0);
        int firstSectorRows = Integer.parseInt(sc.nextLine());
        int placesOddRow = Integer.parseInt(sc.nextLine());
        int placesEvenRow = placesOddRow + 2;
        int sectorRows = firstSectorRows;
        int totalPlaces = 0;

        for (int i = 'A'; i <= lastSector; i++) {
            char c1 = (char) i;

            for (int j = 1; j <= sectorRows; j++) {
                int places = 0;

                if (j % 2 == 0) {
                    places = placesEvenRow;
                } else {
                    places = placesOddRow;
                }
                for (int k = 0; k < places; k++) {
                    char c2 = (char) ('a' + k);

                    System.out.printf("%c%d%c%n",c1,j,c2);
                    totalPlaces++;

                }
            }

            sectorRows++;
        }
        System.out.println(totalPlaces);
    }
}
