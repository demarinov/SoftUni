package com.dido.lab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {

    private static BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {



        int[] array = Arrays.stream(sc.readLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .sorted()
                .toArray();

        int n = Integer.parseInt(sc.readLine());

        int index = binarySearch(array, n);

        System.out.println(index);

    }

    public static int binarySearch(int[] array, int key) {

        int start = 0;
        int end = array.length-1;

        while(start <= end) {

            int mid = start + (end -start)/2;

            if (key < array[mid]) {
                end = mid - 1;
            } else if (key > array[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
