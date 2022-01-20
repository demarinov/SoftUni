package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class RecursiveArraySum {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] array = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

//        int result = findSum(array, 0);
        int result = findSumRev(array, array.length-1);
        // 1 2 3 4
        // 1 - 3,2
        // 2 - 2,1
        // 3 - 1,0
        System.out.println(result);
    }

    public static int findSumRev(int[] array, int n) {

        if (n == 0) {
            return array[0];
        }

        int result = array[n] + findSumRev(array, n-1);
        return result;
    }

    public static int findSum(int[] array, int n) {

        if (n == array.length) {
            return 0;
        }

        int result = array[n] + findSum(array, n+1);
        return result;
    }
}
