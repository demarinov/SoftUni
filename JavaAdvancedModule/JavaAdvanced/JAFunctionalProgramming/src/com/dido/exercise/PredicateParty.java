package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.*;
import java.util.stream.Collectors;

public class PredicateParty {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = Arrays.stream(sc.nextLine().split("\\s"))
                .collect(Collectors.toList());

        // Pesho Misho Stefan
        //Remove StartsWith P
        //Double Length 5
        //Party!

        String input = sc.nextLine();
        BiPredicate<String,String> predicateStart = (s1,s2) -> s1.startsWith(s2);
        BiPredicate<String,String> predicateEnd = (s1,s2) -> s1.endsWith(s2);
        BiPredicate<String,Integer> predicateLength = (s1,s2) -> s1.length() == s2;

        BiConsumer<List<String>, String> consumerAdd = (l,e) -> {

            if (e != null) {
                l.add(0, e);
            }
        };

        while(!"Party!".equals(input)) {

            String[] commandData = input.split("\\s");

            switch(commandData[0]) {

                // StartsWith, EndsWith, Double
                case "Remove":
                    if ("StartsWith".equals(commandData[1])) {
                        names.removeIf(s -> predicateStart.test(s,commandData[2]));
                    } else if ("EndsWith".equals(commandData[1])) {
                        names.removeIf(s -> predicateEnd.test(s,commandData[2]));
                    } else if ("Length".equals(commandData[1])) {
                        names.removeIf(s -> predicateLength.test(s,Integer.parseInt(commandData[2])));
                    }
                    break;
                case "Double":
                    if ("StartsWith".equals(commandData[1])) {
                        List<String> namesList = names.stream().filter(s -> predicateStart.test(s, commandData[2]))
                            .collect(Collectors.toList());
                        for (int i = 0; i < namesList.size(); i++) {
                            String name = namesList.get(i);
                            consumerAdd.accept(names,name);
                        }

                    } else if ("EndsWith".equals(commandData[1])) {
                        List<String> namesList = names.stream().filter(s -> predicateEnd.test(s, commandData[2]))
                                .collect(Collectors.toList());

                        for (int i = 0; i < namesList.size(); i++) {
                            String name = namesList.get(i);
                            consumerAdd.accept(names,name);
                        }
                    } else if ("Length".equals(commandData[1])) {
                        List<String> namesList = names.stream().filter(s -> predicateLength
                                .test(s, Integer.parseInt(commandData[2])))
                                .collect(Collectors.toList());
                        for (int i = 0; i < namesList.size(); i++) {
                            String name = namesList.get(i);
                            consumerAdd.accept(names,name);
                        }
                    }
                    break;

            }

            input = sc.nextLine();
        }

        if (names.isEmpty()) {
            System.out.println("Nobody is going to the party!");
        } else {
            String result = String.join(", ",
                    names.stream().sorted().collect(Collectors.toList()).toArray(new String[0]));
            System.out.println(result + " are going to the party!");
        }
    }
}
