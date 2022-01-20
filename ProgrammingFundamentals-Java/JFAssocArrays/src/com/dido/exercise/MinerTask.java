package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MinerTask {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, Integer> resourceMap = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("stop")) {

            String resource = input;
            String quantity = sc.nextLine();

            resourceMap.putIfAbsent(resource,0);
            resourceMap.put(resource, resourceMap.get(resource)+Integer.valueOf(quantity));

            input = sc.nextLine();
        }

        resourceMap.entrySet().stream()
                .map(e -> String.format("%s -> %d",e.getKey(), e.getValue())).forEach(System.out::println);


    }
}
