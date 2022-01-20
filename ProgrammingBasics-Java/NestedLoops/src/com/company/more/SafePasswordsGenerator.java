package com.company.more;

import java.util.Scanner;

public class SafePasswordsGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());
        int maxPasses = Integer.parseInt(sc.nextLine());
        int count = 0;
        boolean morePasses = true;

        for (int i = 35; i <= 55; ) {

            for (int j = 64; j <= 96; ) {

                for (int k = 1; k <= a; k++) {

                    for (int l = 1; l <= b; l++) {

                        System.out.printf("%c%c%d%d%c%c|", i, j, k, l, j, i);
                        count++;
                        i++;
                        j++;

                        if (i > 55) {
                            i = 35;
                        }
                        if (j > 96) {
                            j = 64;
                        }
                        if (count == maxPasses || (k == a && l == b)) {
                            morePasses = false;
                            break;
                        }
                    }

                    if (!morePasses) {

                        break;
                    }
                }

                if (!morePasses) {

                    break;
                }
            }

            if (!morePasses) {

                break;
            }
        }


    }
}
