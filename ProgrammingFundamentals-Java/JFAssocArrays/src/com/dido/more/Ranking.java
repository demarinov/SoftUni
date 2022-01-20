package com.dido.more;

import java.util.*;

public class Ranking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, String> contestMap = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("end of contests")) {

            String[] inputData = input.split(":");

            String contest = inputData[0];
            String password = inputData[1];
            contestMap.putIfAbsent(contest, password);

            input = sc.nextLine();
        }

//        {contest}=>{password}=>{username}=>{points}"

        input = sc.nextLine();
        Map<String, Map<String, String>> userContestMap = new TreeMap<>();

        while(!input.equalsIgnoreCase("end of submissions")) {

            String[] userData = input.split("=>");
            String contest = userData[0];
            String password = userData[1];
            String user = userData[2];
            String points = userData[3];

            if (contestMap.containsKey(contest)) {

                if (password.equals(contestMap.get(contest))) {
                    Map<String, String> contestPointsMap = new LinkedHashMap<>();

                    if (userContestMap.containsKey(user)) {


                        Map<String, String> currentContestPointsMap = userContestMap.get(user);
                        if (currentContestPointsMap.containsKey(contest)) {

                            String currentPoints = currentContestPointsMap.get(contest);

                            if (Integer.parseInt(currentPoints) < Integer.parseInt(points)) {
                                currentContestPointsMap.put(contest, points);
                            }
                        }
                        currentContestPointsMap.putIfAbsent(contest,points);


                    } else {
                        contestPointsMap.put(contest, points);

                        userContestMap.put(user, contestPointsMap);
                    }


                }
            }



            input = sc.nextLine();
        }


        int maxPoints = 0;
        String bestUser = "";
        for (Map.Entry<String, Map<String, String>> user : userContestMap.entrySet()) {
            String name = user.getKey();
            Map<String, String> contestPointsMap = user.getValue();

            int totalPoints = contestPointsMap.entrySet().stream()
                    .mapToInt(u -> Integer.parseInt(u.getValue())).sum();
            if (maxPoints < totalPoints) {
                maxPoints = totalPoints;
                bestUser = name;
            }
        }

        System.out.println("Best candidate is "+bestUser+" with total "+maxPoints+" points.");


//        all students ordered by their names. For each user print
//        each contest with the points in descending order

        System.out.println("Ranking: ");

        for (Map.Entry<String, Map<String, String>> user : userContestMap.entrySet()) {
            String name = user.getKey();
            Map<String, String> contestPointsMap = user.getValue();
            System.out.println(name);
            contestPointsMap.entrySet().stream()
                    .sorted((u1,u2) -> u2.getValue().compareTo(u1.getValue()))
                    .map(e -> String.format("#  %s -> %s",e.getKey(), e.getValue()))
                    .forEach(System.out::println);
        }

    }

    public static Map<String, String> findContest(List<Map<String, String>> list, String contest) {

        return list.stream().filter(c -> c.containsKey(contest)).map(c -> c).findFirst().orElse(null);
    }
}
