package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MagicSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array =
                Arrays.stream(sc.nextLine()
                        .split(" ")).mapToInt(e -> Integer.parseInt(e)).toArray();

        int num = Integer.parseInt(sc.nextLine());
        int[] arrStore = new int[array.length];

        for (int i = 0; i < array.length-1; i++) {
            int n1 = array[i];

            for (int j = i+1; j < array.length; j++) {

                int n2 = array[j];
                int sum = n1 + n2;

                if (sum == num) {

                    // if unique print
                    boolean isUnique = true;
                    for (int k = 0; k < arrStore.length - 1; k++) {

                        if (arrStore[k] == n1 && arrStore[k+1] == n2) {
                            isUnique = false;
                            break;
                        }
                    }
                    if (isUnique) {
                        System.out.printf("%d %d%n", n1, n2);
                    }
                }
            }

        }
    }
}
