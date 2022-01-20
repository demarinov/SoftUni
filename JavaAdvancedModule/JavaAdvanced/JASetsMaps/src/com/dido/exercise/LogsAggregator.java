package com.dido.exercise;

import javax.print.DocFlavor;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LogsAggregator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        Map<String, Map<String, Integer>> userMap = new TreeMap<>();
//        Map<String, Integer> userTotalDurationMap = new TreeMap<>();
        for (int i = 0; i < count; i++) {

            String[] userData = sc.nextLine().split("\\s");

            String ip = userData[0];
            String user = userData[1];
            Integer duration = Integer.parseInt(userData[2]);

            if (userMap.containsKey(user)) {
                Map<String, Integer> ipMap = userMap.get(user);

                ipMap.putIfAbsent(ip,0);
                ipMap.put(ip, ipMap.get(ip)+duration);
//                userTotalDurationMap.put(user,userTotalDurationMap.get(user)+duration);

            } else {
                Map<String, Integer> ipMap = new TreeMap<>();
                ipMap.put(ip, duration);
                userMap.put(user, ipMap);
//                userTotalDurationMap.put(user, duration);
            }

        }

        userMap.entrySet().stream()
                .forEach(e -> {
                    String user = e.getKey();
                    Integer totalDuration = e.getValue().entrySet().stream()
                            .mapToInt(d -> d.getValue())
                            .sum();
//                    Integer totalDuration = userTotalDurationMap.get(user);

                    StringBuilder builder = new StringBuilder();
//                    String ips = e.getValue().entrySet().stream()
                    String ips = "";
                    e.getValue().entrySet().stream()
                            .forEach(i ->{

                                builder.append(i.getKey());
                                builder.append(", ");

                            });
//                            .map(i -> String.format("%s, ",i.getKey()))
//                            .reduce("",String::concat);
//                    ips = ips.substring(0, ips.length()-2);
                    ips = builder.substring(0, builder.length()-2);

                    System.out.printf("%s: %d [%s]%n",user, totalDuration, ips);

                });
    }
}
