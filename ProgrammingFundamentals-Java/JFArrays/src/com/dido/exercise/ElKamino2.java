package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class ElKamino2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lengthSeq = Integer.parseInt(sc.nextLine());

        if (lengthSeq >=1 && lengthSeq <= 100) {
            String input = sc.nextLine();
            int[] bestSeq = new int[lengthSeq];
            int bestSeqLen = 0;
            int bestPos = lengthSeq - 1;
            int maxSum = 0;
            int bestSeqCount = 0;
            int seqCount = 0;


            while (!input.equals("Clone them!")) {
//            int[] seq = Arrays.stream(input.split("!"))
//                    .mapToInt(e -> Integer.parseInt(e)).toArray();

                seqCount++;
                String[] seqStr = input.split("!+");
                        //= input.replaceAll("!+", "").split("");
                int[] seq = new int[lengthSeq];


                // populate int seq ...
                for (int i = 0; i < lengthSeq; i++) {

                    seq[i] = Integer.parseInt(seqStr[i]);

                }


                // find max subSeq ...
                int subSeqLen = 0;
                int startIdx = -1;
                int sumOfOnesPerSeq = 0;
                int[] bestSubSeqStore = new int[2];
                bestSubSeqStore[0] = -1;

                // find the subSeqs and store them ...
                for (int i = 0; i < seq.length; i++) {

                    if (seq[i] == 1) {

                        if (startIdx == -1) {
                            startIdx = i;
                        }

                        subSeqLen += 1;
                        sumOfOnesPerSeq += 1;

                    }

                    if (seq[i] == 0 || i == seq.length - 1) {

                        if (bestSubSeqStore[1] < subSeqLen) {
                            bestSubSeqStore[0] = startIdx;
                            bestSubSeqStore[1] = subSeqLen;

                        }

                        subSeqLen = 0;
                        startIdx = -1;
                    }


                }

                boolean bestSubSeqCheck = false;

                if (bestSubSeqStore[1] > bestSeqLen) {
                    bestSubSeqCheck = true;
                }

                if (bestSubSeqStore[1] == bestSeqLen && bestSubSeqStore[0] < bestPos) {
                    bestSubSeqCheck = true;
                }

                if (bestSubSeqStore[1] == bestSeqLen && bestSubSeqStore[0] == bestPos
                        && sumOfOnesPerSeq > maxSum) {
                    bestSubSeqCheck = true;
                }

                if (bestSubSeqCheck) {

                    maxSum = sumOfOnesPerSeq;
                    bestPos = bestSubSeqStore[0];
                    bestSeqLen = bestSubSeqStore[1];
                    bestSeq = seq;
                    bestSeqCount = seqCount;

                }

                input = sc.nextLine();
            }


            System.out.printf("Best DNA sample %d with sum: %d.%n" +
                    "%s", bestSeqCount, maxSum, String
                    .join(" ", Arrays.stream(bestSeq).mapToObj(String::valueOf)
                            .toArray(String[]::new)));

        }
    }
}
