package com.company.more;

import java.util.Scanner;

public class PinCodes {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int r1 = Integer.parseInt(sc.nextLine());
        int r2 = Integer.parseInt(sc.nextLine());
        int r3 = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <=r1; i++) {

            if (i % 2 != 0) {
                continue;
            }

            for (int j = 1; j <=r2; j++) {

                if (j < 2 || j > 7) {
                    continue;
                }

                // check for simple number
                boolean isSimple = true;
                for (int l = 2; l < j; l++) {

                    if (j % l == 0) {
                        isSimple = false;
                        break;
                    }
                }

                if (!isSimple) {
                    continue;
                }

                for (int k = 1; k <=r3; k++) {
                    if (k % 2 != 0) {
                        continue;
                    }

                    System.out.printf("%d %d %d%n",i,j,k);
                }
            }
        }


    }
}
