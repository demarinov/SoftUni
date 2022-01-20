package com.dido.exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PoisonousPlants {

    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        String[] tokens = reader.readLine().split("\\s");

        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(tokens[i]));
        }

//        List<Integer> removedNumbers = new LinkedList<>();

        Deque<Integer> removedNumbers = new ArrayDeque<>();
        int countDays = 0;
        while(true) {
            boolean isWeak = false;
            // find weaker plants and mark them
            for (int i = 1; i < nums.size(); i++) {

                int prevNum = nums.get(i - 1);
                int num = nums.get(i);

                if (num > prevNum) {

                    // found weaker pesticide
                    if (!removedNumbers.contains(i)) {
                        removedNumbers.push(i);
                        isWeak = true;
                    }

                }
            }


            // or use stack to reverse the indexes
//            Collections.sort(removedNumbers, (d1,d2) -> d2.compareTo(d1));
            // remove the weaker plants
            for (;!removedNumbers.isEmpty();) {

                int index = removedNumbers.pop();

                nums.remove(index);
            }

            if (!isWeak) {
                break;
            }

            countDays++;
        }

//        nums.stream().forEach(System.out::println);
        System.out.println(countDays);
    }
}
