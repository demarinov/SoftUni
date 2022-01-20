package com.company.more;

import java.util.Scanner;

public class ChallengeTheWedding {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int men = Integer.parseInt(sc.nextLine());
        int women = Integer.parseInt(sc.nextLine());
        int tables = Integer.parseInt(sc.nextLine());
        int pairs = 0;
        boolean freeTable = true;

        for (int i = 1; i <= men; i++) {
            for (int j = 1; j <= women; j++) {

                System.out.printf("(%d <-> %d) ",i, j);
                pairs++;
                if (pairs == tables) {
                    freeTable = false;
                    break;
                }
            }

            if (!freeTable) {
                break;
            }
        }

    }
}
