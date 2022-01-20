package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MinerTask {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        You are given a sequence of strings, each on a new line.
//                Every odd line on the console is representing a resource
//                (e.g. Gold, Silver, Copper, and so on) , and every even – quantity.
//                Your task is to collect the resources and print them each on a new line.
//                Print the resources and their quantities in format:
//        {resource} –> {quantity}

        int count = 0;

        String input = sc.nextLine();

        Map<String, Integer> resources =  new LinkedHashMap<>();
        String resourceName = "";
        while(!input.equals("stop")) {

            count++;

            int qty = 0;
            if (count % 2 == 0) {
                qty = Integer.parseInt(input);
                resources.putIfAbsent(resourceName, 0);
                resources.put(resourceName, resources.get(resourceName) + qty);
            } else {
                resourceName = input;
            }

            input = sc.nextLine();
        }

        resources.entrySet().stream()
                .forEach(r -> System.out.printf("%s -> %d%n",r.getKey(), r.getValue()));
    }
}
