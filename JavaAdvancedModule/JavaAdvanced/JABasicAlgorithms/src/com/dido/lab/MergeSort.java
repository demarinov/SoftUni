package com.dido.lab;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        int[] sortedArray = mergeSort(array);

        System.out.println(Arrays.toString(sortedArray).replaceAll("[,\\[\\]]",""));
    }

    public static int[] mergeSort(int[] array) {

        if (array.length == 1) {
            return array;
        }

        int halfIndex = array.length / 2;
        int firstPartitionLength = halfIndex;
        int secondPartitionLength = array.length - halfIndex;

        int[] firstPartition = new int[firstPartitionLength];
        int[] secondPartition = new int[secondPartitionLength];

        for (int i = 0; i < firstPartitionLength; i++) {
            firstPartition[i] = array[i];
        }

        for (int i = firstPartitionLength; i < firstPartitionLength+secondPartitionLength; i++) {
            secondPartition[i-firstPartitionLength] = array[i];
        }

        firstPartition = mergeSort(firstPartition);
        secondPartition = mergeSort(secondPartition);

        int mainIndex = 0;

        int firstPartitionIndex = 0;
        int secondPartitionIndex = 0;

        while(firstPartitionIndex < firstPartitionLength && secondPartitionIndex < secondPartitionLength) {

            if (firstPartition[firstPartitionIndex] < secondPartition[secondPartitionIndex]) {
                array[mainIndex] = firstPartition[firstPartitionIndex];

                mainIndex++;
                firstPartitionIndex++;
            } else {
                array[mainIndex] = secondPartition[secondPartitionIndex];

                mainIndex++;
                secondPartitionIndex++;
            }

        }

        // left-overs
        while(firstPartitionIndex < firstPartitionLength) {
            array[mainIndex] = firstPartition[firstPartitionIndex];

            mainIndex++;
            firstPartitionIndex++;

        }

        while(secondPartitionIndex < secondPartitionLength) {
            array[mainIndex] = secondPartition[secondPartitionIndex];

            mainIndex++;
            secondPartitionIndex++;

        }

        return array;
    }
}
