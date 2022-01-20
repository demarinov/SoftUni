package com.dido.exercise;

import java.util.Scanner;

public class EqualSums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] array = sc.nextLine().split(" ");
        int elemIdx = -1;

        for (int i = 0; i < array.length; i++) {

            int leftSum = 0;
            int rightSum = 0;

            // sum left
            for (int j = i-1; j >= 0; j--) {
                leftSum += Integer.parseInt(array[j]);
            }

            // sum right
            for (int j = i+1; j < array.length; j++) {
                rightSum += Integer.parseInt(array[j]);
            }

            if (leftSum == rightSum) {
                elemIdx = i;
                break;
            }
        }

        if (elemIdx >= 0) {
            System.out.printf("%d",elemIdx);
        } else {
            System.out.printf("%s","no");
        }
    }
}
