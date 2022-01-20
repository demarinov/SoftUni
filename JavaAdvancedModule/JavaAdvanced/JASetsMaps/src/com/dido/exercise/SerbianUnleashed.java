package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SerbianUnleashed {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        String regex = "^(([A-Za-z]+\\s?){1,3})(?<=[^\\s]) @(([A-Za-z]+\\s?){1,3})(?<=[^\\s]) (\\d+) (\\d+)";
        Pattern pattern = Pattern.compile(regex);


        Map<String, Map<String,Integer>> concertMap = new LinkedHashMap<>();
        while(!input.equals("End")) {

            // singer @venue ticketsPrice ticketsCount
            Matcher matcher = pattern.matcher(input);

            while(matcher.find()) {

                String name = matcher.group(1);
                String venue = matcher.group(3);
                Integer ticketPrice = Integer.parseInt(matcher.group(5));
                Integer ticketCount = Integer.parseInt(matcher.group(6));
                Integer totalTicketPrice = ticketPrice * ticketCount;

                concertMap.putIfAbsent(venue,new LinkedHashMap<>());

                Map<String, Integer> tickets = concertMap.get(venue);

                tickets.putIfAbsent(name, 0);
                tickets.put(name, tickets.get(name)+totalTicketPrice);
            }

            input = sc.nextLine();
        }

        concertMap.entrySet().stream()
                .map(c ->
                {
                    String venue = c.getKey();
                    String ticketValues = c.getValue().entrySet().stream()
                            .sorted((t1,t2) -> t2.getValue().compareTo(t1.getValue()))
                            .map(t ->
                            {

                                return String.format("#  %s -> %d%n",t.getKey(), t.getValue());
                            })
                            .reduce("",String::concat);

                    return String.format("%s%n%s",venue, ticketValues);
                })
                .forEach(System.out::print);
    }
}
