package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
import java.util.Scanner;

public class HotPotato {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Queue<String> names = new ArrayDeque<>();
        Collections.addAll(names,sc.nextLine().split("\\s+"));
        Integer n = Integer.parseInt(sc.nextLine());

        int catchPotato = 1;
        while(names.size() > 1) {

            if (catchPotato == n) {
                System.out.println("Removed "+ names.poll());
                catchPotato = 1;
                continue;
            }

            String name  = names.poll();
            names.offer(name);

            catchPotato++;
        }

        System.out.println("Last is "+names.peek());
    }
}
