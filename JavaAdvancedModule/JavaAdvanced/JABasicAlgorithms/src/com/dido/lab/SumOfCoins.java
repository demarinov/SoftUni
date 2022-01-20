package com.dido.lab;

import java.util.*;

public class SumOfCoins {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(",\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .sorted()
                .toArray();
        int sum = Integer.parseInt(sc.nextLine());

        Map<Integer, Integer> coinMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < 1; i++) {

            int result = sumOfCoins(nums, nums.length-1,sum, coinMap);
            System.out.println("Number of coins to take: "+result);

            coinMap.entrySet().stream()
                    .forEach(e ->
                            System.out.printf("%d coin(s) with value %d%n",e.getValue(), e.getKey()));
        }
    }

    public static int sumOfCoins(int[] nums, int index , int sum, Map<Integer, Integer> coinMap) {

        int count = sum / nums[index];
        int rem = sum % nums[index];
        if (index == 0 || rem == 0) {

            if (rem == 0) {
                coinMap.put(nums[index],count);
                return count;
            } else {
                throw new ArithmeticException("Error");
            }
        }

        if (count != 0) {
            coinMap.put(nums[index], count);
        }
        int result = count + sumOfCoins(nums, index-1,rem, coinMap);

        return result;
    }
}
