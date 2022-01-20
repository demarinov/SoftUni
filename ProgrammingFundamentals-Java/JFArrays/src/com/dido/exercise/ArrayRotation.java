package com.dido.exercise;

import java.util.Scanner;

public class ArrayRotation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] array = sc.nextLine().split(" ");
        int r = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < r; i++) {

            String firstElem = array[0];
            for (int j = 1; j < array.length; j++) {
                // shift back
                array[j-1] = array[j];
            }
            array[array.length - 1] = firstElem;
        }

        for (int i = 0; i < array.length; i++) {

            System.out.printf("%s ",array[i]);
        }
    }
}
