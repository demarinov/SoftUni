package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ForceBook {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, String> userSideMap = new LinkedHashMap<>();
        Map<String, List<String>> sideUserMap = new LinkedHashMap<>();

        String pattern = "( \\| )|( -> )";

        while(!input.equalsIgnoreCase("lumpawaroo")) {

//            {forceSide} | {forceUser}
//            you should check if such forceUser already exists,
//            and if not - add him/her to the corresponding side.
//            {forceUser} -> {forceSide}
//            you should check if there is such forceUser already and if so,
//            change his/her side. If there is no such forceUser,
//            add him/her to the corresponding forceSide,
//            treating the command as new registered forceUser.

            String[] forceData = input.split(pattern);

            if (input.contains("|")) {
                String user = forceData[1];
                String side = forceData[0];

                if (!userSideMap.containsKey(user)) {
                    if (sideUserMap.containsKey(side)) {
                        List<String> users = sideUserMap.get(side);

                        if (!users.contains(user)) {
                            users.add(user);
                        }
                    } else {
                        List<String> users = new ArrayList<>();
                        users.add(user);
                        sideUserMap.put(side, users);
                    }

                    userSideMap.put(user,side);
                }


            } else if (input.contains("->")) {
                String user = forceData[0];
                String side = forceData[1];
                if (userSideMap.containsKey(user)) {
                    String oldSide = userSideMap.get(user);
                    List<String> users = sideUserMap.get(oldSide);
                    users.remove(user);

                }

                System.out.println(user+" joins the "+side+" side!");

                if (sideUserMap.containsKey(side)) {
                    List<String> users = sideUserMap.get(side);

                    if (!users.contains(user)) {
                        users.add(user);
                    }
                } else {
                    List<String> users = new ArrayList<>();
                    users.add(user);
                    sideUserMap.put(side, users);
                }

                // change the side or add new one...
                userSideMap.put(user,side);
            }
            input = sc.nextLine();
        }


        List<Map.Entry<String, List<String>>> sortedSideList = sideUserMap.entrySet()
                .stream().sorted((s1,s2) -> {
                    int result = s2.getValue().size() - s1.getValue().size();

                    return result == 0 ? s1.getKey().compareTo(s2.getKey()) : result;
                }).collect(Collectors.toList());

        for (Map.Entry<String, List<String>> entry : sortedSideList) {

            String side = entry.getKey();
            List<String> users = entry.getValue();

            if (users.size() > 0) {
                System.out.printf("Side: %s, Members: %d%n", side, users.size());
            }

            users.stream()
                    .sorted((s1,s2) -> s1.compareTo(s2))
                    .map(u -> String.format("! %s",u)).forEach(System.out::println);
        }
    }
}
