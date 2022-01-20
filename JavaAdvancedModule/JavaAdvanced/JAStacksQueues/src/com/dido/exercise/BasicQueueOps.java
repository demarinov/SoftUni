package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class BasicQueueOps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] values = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(d -> Integer.parseInt(d)).toArray();

        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(d -> Integer.parseInt(d)).toArray();



        int n = values[0];
        int s = values[1];
        int x = values[2];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            queue.offer(numbers[i]);
        }

        if (!queue.isEmpty()) {
            for (int i = 0; i < s; i++) {
                queue.poll();
            }

            boolean present = queue.stream().filter(d -> d.equals(x)).findFirst().isPresent();

            if (present) {
                System.out.println("true");
            } else {
                int min = queue.stream().mapToInt(d -> d).min().orElse(0);
                System.out.println(min);
            }
        }

    }
}
