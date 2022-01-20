package com.dido.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class Some {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());//length of the sequences – integer in range [1…100];
        String code = scanner.nextLine();

        int numberDNAtest = 0;//real = 0+1
        int[] bestLenghtInRow = new int[1000];// All best One`s  lenght
        int[] bestPositionInRow = new int[1000];// All best position in row
        int[] sumRow = new int[1000];// all sum
        String[] DNA = new String[1000]; // All sequences


        while (!"Clone them!".equals(code)) {
            code = code.replaceAll("!+", "");
            DNA[numberDNAtest] = code;

            int[] num = Arrays.stream(code.split("")).mapToInt(e -> Integer.parseInt(e)).toArray();
            int[] len = new int[num.length];

            for (int i = 0; i < num.length; i++) { // line long seq of Ones +sum
                if (num[i] == 1) {
                    len[i] = 1;
                    for (int j = i + 1; j < num.length; j++) {
                        if (num[j] == 1) {
                            len[i] += 1;
                        } else {
                            break;
                        }
                    }
                }
                sumRow[numberDNAtest] += num[i];
            }

            for (int i = 0; i < num.length; i++) { // best lenght in row goes to Array
                if (len[i] > bestLenghtInRow[numberDNAtest]) {
                    bestLenghtInRow[numberDNAtest] = len[i];
                    bestPositionInRow[numberDNAtest] = i;
                }
            }
            numberDNAtest++;
            code = scanner.nextLine();
        }

        int grandLenght = 0;
        int grandposition = 0;
        String grand = DNA[0];
        int grandSum = 0;
        int grandRowNum = 1;

        for (int i = 0; i < numberDNAtest; i++) {
            if (bestLenghtInRow[i] > grandLenght ||
                    (bestLenghtInRow[i] == grandLenght && grandposition > bestPositionInRow[i]) ||
                    (bestLenghtInRow[i] == grandLenght && grandposition == bestPositionInRow[i] && grandSum < sumRow[i])) {
                grandLenght = bestLenghtInRow[i];
                grandposition = bestPositionInRow[i];
                grand = DNA[i];
                grandSum = sumRow[i];
                grandRowNum = i + 1;
            }
        }
        grand = grand.replaceAll("", " ");
        grand = grand.substring(1);
        System.out.printf("Best DNA sample %d with sum: %d.%n%s", grandRowNum, grandSum, grand);

    }
}
