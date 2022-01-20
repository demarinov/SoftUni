package com.company.lab;

import java.util.Scanner;

public class Rooms {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int floors = Integer.parseInt(sc.nextLine());
        int rooms = Integer.parseInt(sc.nextLine());

        for (int i = floors; i > 0; i--) {

            char c = '\0';

            if (i % 2 == 0) {
                c = 'O';
            } else {
                c = 'A';
            }

            for (int j = 0; j < rooms; j++) {
                if (i == floors) {
                    // Output last floor first
                    System.out.printf("L%d%d ",i,j);
                } else {
                    System.out.printf("%c%d%d ",c,i,j);
                }

            }
            System.out.println();
        }
    }
}
