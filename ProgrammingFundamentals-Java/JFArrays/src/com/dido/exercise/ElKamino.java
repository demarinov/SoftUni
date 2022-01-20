package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ElKamino {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lengthSeq = Integer.parseInt(sc.nextLine());

        if (lengthSeq >=1 && lengthSeq <= 100) {
            String input = sc.nextLine();

            int[] bestSeq = new int[lengthSeq];
            int bestSeqLen = 0;
            int bestSeqPos = lengthSeq - 1;;
            int bestSeqSum = 0;
            int seqCounter = 0;
            int bestSeqCounter = 0;


            while (!input.equals("Clone them!")) {

                String[] seqStr = input.replaceAll("!+","").split("");
                int[] seq = new int[lengthSeq];
                seqCounter++;

                // populate int seq ...
                for (int i = 0; i < seqStr.length; i++) {

                    seq[i] = Integer.parseInt(seqStr[i]);

                }


                // find max subSeq ...
                int bestSubSeqLen= 0;
                int curSubSeqLen= 0;
                int curOnesStartIdx = -1;
                int bestSubSeqStartIdx = -1;
                int prevElem = Integer.MIN_VALUE;
                int sumOfOnesPerSeq = 0;

                // find the subSeqs and store them ...
                for (int i = 0; i < seq.length; i++) {

                    if (seq[i] == 1) {

                        if (seq[i] != prevElem) {
                            curOnesStartIdx = i;

                        }

                        curSubSeqLen += 1;
                        sumOfOnesPerSeq += 1;

                    }

                    if (seq[i] == 0 || i == seq.length - 1) {
                        // check for best subSeq
                        if (bestSubSeqLen < curSubSeqLen) {
                            bestSubSeqLen = curSubSeqLen;
                            bestSubSeqStartIdx = curOnesStartIdx;
                        }

                        curSubSeqLen = 0;
                        curOnesStartIdx = -1;

                    }

                    prevElem = seq[i];
                }

                // find the best seq with best subseq
                boolean isBestSeq = false;

                if (bestSeqLen < bestSubSeqLen) {
                    isBestSeq = true;
                }

                if (bestSeqLen == bestSubSeqLen && bestSeqPos > bestSubSeqStartIdx) {
                    isBestSeq = true;
                }

                if (bestSeqLen == bestSubSeqLen && bestSeqPos == bestSubSeqStartIdx
                        && bestSeqSum < sumOfOnesPerSeq) {

                    isBestSeq = true;
                }

                if (isBestSeq) {
                    bestSeqLen = bestSubSeqLen;
                    bestSeqPos = bestSubSeqStartIdx;
                    bestSeqSum = sumOfOnesPerSeq;
                    bestSeqCounter = seqCounter;
                    bestSeq = seq;
                }

                input = sc.nextLine();
            }


            System.out.printf("Best DNA sample %d with sum: %d.%n" +
                    "%s", bestSeqCounter, bestSeqSum, String
                    .join(" ", Arrays.stream(bestSeq).mapToObj(String::valueOf)
                            .toArray(String[]::new)));
        }
    }
}
