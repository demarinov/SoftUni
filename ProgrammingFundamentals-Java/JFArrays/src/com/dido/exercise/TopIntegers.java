package com.dido.exercise;

import java.util.Scanner;

public class TopIntegers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] array = sc.nextLine().split(" ");
        int[] tops = new int[array.length];
        int countTops = 0;

        for (int i = 0; i < array.length; i++) {
            boolean isTop = true;
            int n1 = Integer.parseInt(array[i]);

            for (int j = i+1; j < array.length; j++) {
                int n2 = Integer.parseInt(array[j]);

                if (n1 <= n2) {
                    isTop = false;
                    break;
                }

            }

            if (isTop) {
                tops[countTops] = n1;
                countTops++;
            }
        }

        for (int i = 0; i < countTops; i++) {

            System.out.printf("%d ",tops[i]);
        }
    }
}
