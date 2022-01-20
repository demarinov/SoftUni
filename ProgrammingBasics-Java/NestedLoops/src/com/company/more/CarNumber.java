package com.company.more;

import java.util.Scanner;

public class CarNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int r1 = Integer.parseInt(sc.nextLine());
        int r2 = Integer.parseInt(sc.nextLine());

        for (int i = r1; i <= r2; i++) {
            for (int j = r1; j <= r2; j++) {
                for (int k = r1; k <= r2; k++) {
                    for (int l = r1; l <= r2; l++) {

                        if (i % 2 == 0 && l % 2 == 0) {
                            continue;
                        } else if (i % 2 != 0 && l % 2 != 0) {
                            continue;
                        } else if (i <= l) {
                            continue;
                        }  else if ((j + k) % 2 != 0) {
                            continue;
                        }

                        System.out.printf("%d%d%d%d ",i,j,k,l);
                    }
                }
            }
        }
    }
}
