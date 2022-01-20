package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class KaminoFactory {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lengthSeq = Integer.parseInt(sc.nextLine());
        String input = sc.nextLine();
        int[] bestSeqStore = new int[lengthSeq * 2];
        int[] bestSeqStoreAll = new int[lengthSeq];
        int bestSeqIdx = 0;
        int bestSeqSum = 0;
        int seqCount = 0;
        int realSeqCount = 0;

        while(!input.equals("Clone them!")) {
            int[] seq = Arrays.stream(input.split("!"))
                        .mapToInt(e -> Integer.parseInt(e)).toArray();


            // find max subSeq ...
            int[] seqArray = new int[seq.length * 2];
            int lastSeq = -2;
            int prevElem = Integer.MIN_VALUE;

            for (int i = 0; i < seq.length; i++) {

                int elem = seq[i];

                if (elem != prevElem) {
                    lastSeq+=2;
                    seqArray[lastSeq] = i;
                    seqArray[lastSeq + 1] += 1;
                } else {
                    seqArray[lastSeq + 1] += 1;
                }

                prevElem = elem;
            }

            int maxReps = 0;
            int maxIdx = 0;

            for (int i = 0; i < seqArray.length; i+=2) {

               // System.out.printf("%d -> %d%n",seqArray[i],seqArray[i+1]);

                if (maxReps < seqArray[i+1]) {
                    maxReps = seqArray[i+1];
                    maxIdx = i;
                }
            }

            // store max subseq idx for each sequence
            if (bestSeqStore[seqCount+1] == seqArray[maxIdx+1]) {

                if (bestSeqStore[seqCount] > seqArray[maxIdx]) {
                    bestSeqStore[seqCount] = seqArray[maxIdx];
                    bestSeqStoreAll = seq;
                    bestSeqIdx = realSeqCount;
                } else if (bestSeqStore[seqCount] == seqArray[maxIdx]) {

                    // calc bigger sum
                    int sumBest = 0;
                    for (int i = 0; i < bestSeqStoreAll.length; i++) {
                        sumBest += bestSeqStoreAll[i];
                    }

                    int sum = 0;
                    for (int i = 0; i < seq.length; i++) {
                        sum += seq[i];
                    }

                    if (sum > sumBest) {
                        bestSeqStore[seqCount] = seqArray[maxIdx];
                        bestSeqStoreAll = seq;
                        bestSeqIdx = realSeqCount;
                    }

                }


            } else if (bestSeqStore[seqCount+1] < seqArray[maxIdx+1]) {
                bestSeqStore[seqCount] = seqArray[maxIdx];
                bestSeqStore[seqCount + 1] = seqArray[maxIdx + 1];
                bestSeqStoreAll = seq;
                bestSeqIdx = realSeqCount;

            }

            //seqCount+=2;
            /////////////////
            realSeqCount++;
            input = sc.nextLine();
        }


        for (int i = 0; i < bestSeqStoreAll.length; i++) {

            bestSeqSum += bestSeqStoreAll[i];
        }
        System.out.printf("Best DNA sample %d with sum: %d.%n" +
                "%s",bestSeqIdx+1,bestSeqSum,String
                .join(" ",Arrays.stream(bestSeqStoreAll).mapToObj(String::valueOf)
                        .toArray(String[]::new)));
    }
}
