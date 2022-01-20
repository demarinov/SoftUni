package com.company.more;

import java.util.Scanner;

public class SecretDoor {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int r1 = Integer.parseInt(sc.nextLine());
        int r2 = Integer.parseInt(sc.nextLine());
        int r3 = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= r1; i++) {
            if (i % 2 != 0) {
                continue;
            }
            for (int j = 1; j <= r2; j++) {

                // can be simplified a lot ...
                if (j == 1) {
                    continue;
                }

                // simple num
                boolean notPrime = false;
                for (int k = 2; k <=7; k++) {

                    if (k!=j && j % k ==0) {
                        notPrime = true;
                        break;
                    }
                }

                if (notPrime) {
                    continue;
                }

                for (int k = 1; k <= r3; k++) {
                    if (k % 2 != 0) {
                        continue;
                    }

                    System.out.printf("%d %d %d%n",i,j,k);
                }
            }
        }
    }
}
