package com.dido.lab;

import java.util.*;

public class SumOfCoinsV2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split(",\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .sorted()
                .toArray();
        int sum = Integer.parseInt(sc.nextLine());

        Map<Integer, Integer> coinsMap = chooseCoins(nums, sum);

        if (coinsMap != null && !coinsMap.isEmpty()) {
            int count = coinsMap.entrySet().stream().mapToInt(e -> e.getValue()).sum();
            System.out.println("Number of coins to take: " + count);
            coinsMap.entrySet().stream()
                    .forEach(e ->
                            System.out.printf("%d coin(s) with value %d%n", e.getValue(), e.getKey()));
        }
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        int rem = targetSum;
        Map<Integer, Integer> coinsMap = new TreeMap<>(Comparator.reverseOrder());
        for (int i = coins.length-1; i >= 0; i--) {

            if (rem == 0) {
                break;
            }

            int count = rem / coins[i];
            rem = rem % coins[i];

            if (count != 0) {
                coinsMap.put(coins[i],count);
            }
        }

        if (rem != 0) {
//            throw new ArithmeticException("Error");
            System.out.println("Error");
            return null;
        }

        return coinsMap;
    }

}
