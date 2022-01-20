package com.dido.exercise;

import java.util.Scanner;

public class MaxSeqEqElems {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] array = sc.nextLine().split(" ");
        int[] seqArray = new int[array.length * 2];
        int lastSeq = -2;
        int prevElem = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {

            int elem = Integer.parseInt(array[i]);

            if (elem != prevElem) {
                lastSeq+=2;
                seqArray[lastSeq] = elem;
                seqArray[lastSeq + 1] += 1;
            } else {
                seqArray[lastSeq + 1] += 1;
            }

            prevElem = elem;
        }

        int maxReps = 0;
        int maxIdx = 0;

        for (int i = 0; i < seqArray.length; i+=2) {

            //System.out.printf("%d -> %d%n",seqArray[i],seqArray[i+1]);

            if (maxReps < seqArray[i+1]) {
                maxReps = seqArray[i+1];
                maxIdx = i;
            }
        }

        for (int i = 0; i < maxReps; i++) {

            System.out.printf("%d ",seqArray[maxIdx]);
        }
    }
}
