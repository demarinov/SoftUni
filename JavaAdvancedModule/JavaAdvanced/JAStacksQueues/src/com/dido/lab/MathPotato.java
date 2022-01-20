package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;

public class MathPotato {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Queue<String> names = new ArrayDeque<>();
        Collections.addAll(names,sc.nextLine().split("\\s+"));
        Integer n = Integer.parseInt(sc.nextLine());

        int cycles = 1;
        while(names.size() > 1) {

            for (int i = 1; i < n; i++) {
                names.offer(names.poll());
            }

            if (isPrime(cycles)) {
                System.out.println("Prime "+names.peek());
            } else {
                System.out.println("Removed "+names.poll());
            }

            cycles++;
        }

        System.out.println("Last is "+names.poll());
    }

    public static boolean isPrime(Integer n) {

        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= n/2; i++) {

            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
