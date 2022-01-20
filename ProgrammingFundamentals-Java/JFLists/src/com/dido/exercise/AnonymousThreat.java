package com.dido.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AnonymousThreat {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> data = new ArrayList<>(Arrays.asList(sc.nextLine().split("\\s+")));

        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("3:1")) {

            if (command.toLowerCase().contains("merge")) {

                int startIdx = Integer.parseInt(command.split("\\s+")[1]);
                int endIdx = Integer.parseInt(command.split("\\s+")[2]);

                mergeData(data,startIdx, endIdx);

            } else if (command.toLowerCase().contains("divide")) {
                int index = Integer.parseInt(command.split("\\s+")[1]);
                int partitions = Integer.parseInt(command.split("\\s+")[2]);
                divideData(data, index, partitions);
            }

            command = sc.nextLine();
        }

        System.out.println(String.join(" ",data));
    }

    public static void divideData(List<String> data, int index, int partitions) {

        if (partitions == 0 || index >= data.size()) {
            return;
        }

        if (partitions < 0 || partitions > 100) {
            return;
        }

        int dataLen = data.get(index).length();
        int partitionLen =  dataLen / partitions;
        List<String> divElements = new ArrayList<>();
        int nextPartitionIdx = 0;
        if (partitionLen == 0) {
            return;
        }


        int remainder = dataLen % partitions;
        if (remainder == 0) {
            for (int i = 0; i < partitions; i++) {

                divElements.add(data.get(index).substring(nextPartitionIdx,nextPartitionIdx+partitionLen));
                nextPartitionIdx+=partitionLen;
            }


        } else {
            for (int i = 0; i < partitions; i++) {

                if (i == partitions - 1) {

                    partitionLen+= remainder;
                }
                divElements.add(data.get(index).substring(nextPartitionIdx,nextPartitionIdx+partitionLen));
                nextPartitionIdx+=partitionLen;
            }
        }

            data.remove(index);
            data.addAll(index, divElements);
    }

    public static void mergeData(List<String> data, int start, int end) {

        // provided that end > start always

        if (start < 0) {
            start = 0;
        }

        if (end >= data.size()) {
            end = data.size() - 1;
        }

        if (start >= end) {
            return;
        }

        String result = "";

        for (int i = start; i <= end; i++) {
            result += data.get(start);
            data.remove(start);

        }

        data.add(start,result);
    }
}
