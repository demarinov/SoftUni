package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class PrinterQueue {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Queue<String> resources = new ArrayDeque<>();
        while(!input.equalsIgnoreCase("print")) {

            if (input.equalsIgnoreCase("cancel")) {

                if (resources.size() >= 1) {
                    System.out.println("Canceled "+resources.poll());
                } else {
                    System.out.println("Printer is on standby");
                }

                input = sc.nextLine();
                continue;
            }

            String resource = input;
            resources.offer(resource);

            input = sc.nextLine();
        }

        resources.stream().forEach(System.out::println);

    }
}
