package com.company.more;

import java.util.Scanner;

public class LettersCombinations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char cr1 = sc.nextLine().charAt(0);
        char cr2 = sc.nextLine().charAt(0);
        char cr3 = sc.nextLine().charAt(0);
        int combinations = 0;


        for (int i = cr1; i <= cr2; i++) {
            if ((char) i == cr3) {
                continue;
            }
            for (int j = cr1; j <= cr2; j++) {
                if ((char) j == cr3) {
                    continue;
                }
                for (int k = cr1; k <= cr2; k++) {
                    if ((char) k == cr3) {
                        continue;
                    }

                    System.out.printf("%c%c%c ",i,j,k);
                    combinations++;
                }
            }
        }

        System.out.printf("%d%n",combinations);
    }
}
