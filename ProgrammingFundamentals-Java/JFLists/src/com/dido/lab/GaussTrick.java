package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTrick {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int midIndex = nums.size() / 2;

        for (int i = 0; i < midIndex; i++) {

            // sum elements
            Integer tempNum = nums.get(i);
            nums.set(i,nums.get(nums.size() - 1) + tempNum);
            nums.remove(nums.size() - 1);
        }

        System.out.println(nums.toString().replaceAll("[\\[\\],]",""));
    }
}
