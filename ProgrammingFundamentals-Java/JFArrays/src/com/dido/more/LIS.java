package com.dido.more;

import java.util.Arrays;
import java.util.Scanner;

// Longest Increasing Subsequence
public class LIS {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums  = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(s->Integer.parseInt(s)).toArray();


        int[] seqLen = new int[nums.length];
        int[] prevIdx = new int[nums.length];
        int left = -1;

        for (int i = 0; i < nums.length; i++) {



            // first part - init corner case ...
            if (i == 0) {
                prevIdx[i] = left;
                left = i;
                seqLen[i] = 1;
                continue;
            }

            // 2nd part - find the largest possible length to the left ...
            int maxLen = 0;
            int maxIdx = -1;
            while(true) {

                if (nums[i] > nums[left]) {
                    if (maxLen <= seqLen[left]) {
                        maxLen = seqLen[left];
                        maxIdx = left;
                    }
                }

                // find the best length
                left--;

                if (left < 0) {
                    break;
                }

            }

            if (maxIdx < 0) {
                seqLen[i] = 1;
            } else {
                seqLen[i] = maxLen + 1;

            }

            prevIdx[i] = maxIdx;
            left = i;

            // debug ...
//            System.out.println(i);

        }

        // print the seq lengths and prevIds - debug ...
//        for (int i = 0; i < seqLen.length; i++) {
//
//            System.out.printf("%d ",seqLen[i]);
//        }
//
//        System.out.println();
//        for (int i = 0; i < prevIdx.length; i++) {
//
//            System.out.printf("%d ",prevIdx[i]);
//        }
//
//        System.out.println();


        // find the leftmost max seq length ...
        int maxLenIdx = -1;
        int maxLen = 0;

        for (int i = 0; i < seqLen.length; i++) {

            if (maxLen < seqLen[i]) {
                maxLen = seqLen[i];
                maxLenIdx = i;
            } else if (maxLen == seqLen[i]) {
                if (nums[i] > nums[maxLenIdx]) {
                    maxLen = seqLen[i];
                    maxLenIdx = i;
                }
            }

        }

        // populate the final seq...
        int firstIdx = maxLenIdx;
        int[] finalSeq = new int[maxLen];
        int countSeq = maxLen-1;

        while (firstIdx >= 0) {

            finalSeq[countSeq--] = nums[firstIdx];

            firstIdx = prevIdx[firstIdx];

        }

        for (int i = 0; i < finalSeq.length; i++) {

            System.out.printf("%d ",finalSeq[i]);
        }
    }

}
