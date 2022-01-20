package com.dido.more;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Judge {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input =sc.nextLine();

        Map<String, Map<String, Integer>> contestUserMap = new LinkedHashMap<>();
        Map<String, Map<String, Integer>> userContestMap = new LinkedHashMap<>();
        Map<String, Integer> userTotalPoints = new TreeMap<>();

        while(!input.equalsIgnoreCase("no more time")) {

//            {username} -> {contest} -> {points}

            String[] inputData = input.split(" -> ");
            String userName = inputData[0];
            String contest = inputData[1];
            Integer points = Integer.parseInt(inputData[2]);
            Map<String, Integer> userPointsMap = new LinkedHashMap<>();

//            if (!userContestMap.containsKey(userName)) {
//                userContestMap.put(userName, new LinkedHashMap<>());
//            }
//
//            if (!userContestMap.get(userName).containsKey(contest)) {
//                userContestMap.get(userName).put(contest, points);
//            }
//
//            if (userContestMap.get(userName).get(contest) < points) {
//                userContestMap.get(userName).put(contest, points);
//            }
//
//            if (!contestUserMap.containsKey(contest)) {
//                contestUserMap.put(contest, new LinkedHashMap<>());
//            }
//
//            if (!contestUserMap.get(contest).containsKey(userName)) {
//                contestUserMap.get(contest).put(userName, points);
//            }
//
//            if (contestUserMap.get(contest).get(userName) < points) {
//                contestUserMap.get(contest).put(userName, points);
//            }

            if (contestUserMap.containsKey(contest)) {


                Map<String, Integer> currentUserPointsMap = contestUserMap.get(contest);
                if (currentUserPointsMap.containsKey(userName)) {

                    Integer currentPoints = currentUserPointsMap.get(userName);

                    if (currentPoints < points) {
                        currentUserPointsMap.put(userName, points);

                        // little hack to calc the correct total points per user
                        userTotalPoints.put(userName,
                                userTotalPoints.get(userName) - currentPoints);
                    } else {
                        // little hack to calc the correct total points per user
                        userTotalPoints.put(userName,
                                userTotalPoints.get(userName) - points);
                    }
                }
                currentUserPointsMap.putIfAbsent(userName,points);


            } else {
                userPointsMap.put(userName, points);

                contestUserMap.put(contest, userPointsMap);
            }

//             collect also the total points by user
            userTotalPoints.putIfAbsent(userName,0);
            userTotalPoints.put(userName, userTotalPoints.get(userName)+points);

            input = sc.nextLine();
        }

//        for (Map.Entry<String, Map<String, Integer>> userContest: userContestMap.entrySet()) {
//            String user = userContest.getKey();
//            Map<String, Integer> contestPoints = userContest.getValue();
//
//            userTotalPoints.put(user,contestPoints.entrySet().stream()
//                    .mapToInt(s -> s.getValue())
//                    .sum());
//        }


        // can also collect the individual standings below ...
        for (Map.Entry<String, Map<String, Integer>> contest : contestUserMap.entrySet()) {

            String name = contest.getKey();
            Map<String, Integer> userPointsMap = contest.getValue();

            AtomicInteger count = new AtomicInteger(0);
            System.out.println(name+": "+userPointsMap.entrySet().size()+" participants");

            userPointsMap.entrySet().stream()
                    .sorted((s1, s2) -> {

                        int result = s2.getValue().compareTo(s1.getValue());

                        return result == 0 ? s1.getKey().compareTo(s2.getKey()) : result;
                    })
                    .map(s -> {
                        return String.format("%d. %s <::> %d",count.incrementAndGet(),s.getKey(), s.getValue());
                    })
                    .forEach(System.out::println);



        }

        System.out.println("Individual standings:");
        AtomicInteger countUsers = new AtomicInteger(0);

        userTotalPoints.entrySet().stream()
                .sorted((s1, s2) -> {

                    int result = s2.getValue().compareTo(s1.getValue());

                    return result == 0 ? s1.getKey().compareTo(s2.getKey()) : result;
                })
                .map(s -> {
                    return String.format("%d. %s -> %d",countUsers.incrementAndGet(),s.getKey(), s.getValue());
                }).forEach(System.out::println);

    }
}
