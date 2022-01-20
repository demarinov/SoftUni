package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class CommonElements {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] ar1 = sc.nextLine().split(" ");
        String[] ar2 = sc.nextLine().split(" ");

        for (int i = 0; i < ar2.length; i++) {

            for (int j = 0; j < ar1.length; j++) {

                if (ar2[i].equals(ar1[j])) {
                    System.out.printf("%s ",ar2[i]);
                }
            }

        }

    }
}
