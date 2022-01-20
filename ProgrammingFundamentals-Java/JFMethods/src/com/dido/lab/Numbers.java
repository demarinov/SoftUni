package com.dido.lab;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Numbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        printTopFiveNumbers(nums);
    }

    public static void printTopFiveNumbers(int[] nums) {

        // print the top 5 numbers that are greater than the average value
        // in the sequence, sorted in descending order.

        // 1 2 3 4 5 6 -> av 3.5 top(4,5,6)
        // -1 2 3 -5 6 7 -> av 2  top (3,6,7)

        // All input numbers are integers in range [-1 000 000 … 1 000 000].
        // The count of numbers is in range [1…10 000].

        int[] topFiveNums = new int[5];
        int[] topNums = new int[nums.length];
        double sum = 0.0d;

        // init to out of range and calculate av
        for (int i = 0; i < nums.length; i++) {
            topNums[i] = -1000001;
            sum += nums[i];
        }

        // init the top5 nums
        for (int i = 0; i < topFiveNums.length; i++) {
            topFiveNums[i] = -1000001;
        }

        double average = sum / nums.length;
        int j = 0;
        // get the all top nums
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > average) {
                topNums[j] = nums[i];
                j++;
            }
        }

        // get the 5 top nums
        topFiveNums[0] = topNums[0];
        int maxNum = topNums[0];
        int maxIdx = 0;
        int repeatSteps = 0;

        if (topNums.length > 5) {

            repeatSteps = 4;
        } else {
            repeatSteps = topNums.length;
        }

        for (int i = 0; i <= repeatSteps; i++) {

            for (int k = 0; k < topNums.length; k++) {

                if (topNums[k] <= -1000001) {
                    continue;
                }
//{30, 40, 50, 50, 60, 60, 51}.
                if (maxNum < topNums[k]) {
                    maxNum = topNums[k];
                    maxIdx = k;
                }
            }

            if (maxNum >= -1000000 && maxNum <= 1000000) {
                topFiveNums[i] = maxNum;
                maxNum = -1000001;
                topNums[maxIdx] = maxNum;
            }


        }

        boolean hasTopFive = false;
        for (int i = 0; i < topFiveNums.length; i++) {
            if (topFiveNums[i] > -1000001) {
                System.out.printf("%d ", topFiveNums[i]);
                hasTopFive = true;
            }
        }

        if (!hasTopFive) {
            System.out.println("No");
        }

    }
}
