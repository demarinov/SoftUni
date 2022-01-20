package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class EqualArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();
        int[] arr2 = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();


        int maxLen = 0;
        int[] refArr1 = new int[arr1.length];
        int[] refArr2 = new int[arr2.length];

        if (arr1.length > arr2.length) {
            maxLen = arr1.length;
            refArr2 = new int[maxLen];

        } else {
            maxLen = arr2.length;
            refArr1 = new int[maxLen];
        }

        for (int i = 0; i < arr2.length; i++) {
            refArr2[i] = arr2[i];
        }

        for (int i = 0; i < arr1.length; i++) {
            refArr1[i] = arr1[i];
        }


        int firstSum = 0;
        boolean areIdentical = true;
        for (int i = 0; i < maxLen; i++) {
            firstSum += refArr1[i];
            if (refArr1[i] != refArr2[i]) {
                areIdentical = false;
                System.out.printf("Arrays are not identical. Found difference at %d index.",i);
                break;
            }

        }

        if (areIdentical) {
            System.out.printf("Arrays are identical. Sum: %d",firstSum);
        }



    }
}
