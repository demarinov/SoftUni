package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] array = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        sortQuick(array, 0, array.length-1);

        System.out.println(Arrays.toString(array).replaceAll("[,\\[\\]]",""));

    }

    public static void sortQuick(int[] array, int low, int high) {


        if (low < high) {
            int pi = partition(array, low, high);

            sortQuick(array, low, pi - 1);
            sortQuick(array, pi + 1, high);
        }
    }

    public static int partition(int[] array, int low, int high) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (array[j] <= pivot) {
                i++;

                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

        // move pivot as well
        int temp = array[high];
        array[high] = array[i+1];
        array[i+1] = temp;

        return i+1;
    }
}
