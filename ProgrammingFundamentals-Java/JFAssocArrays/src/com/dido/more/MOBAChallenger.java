package com.dido.more;

import java.util.*;
import java.util.stream.Collectors;

public class MOBAChallenger {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        "{player} -> {position} -> {skill}"

        String input = sc.nextLine();

        Map<String, Map<String, Integer>> playerPool = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("season end")) {
            if (input.contains("->")) {
                String[] playerData = input.split(" -> ");
                addNewPlayer(playerPool, playerData);
            } else if (input.contains(" vs ")) {
                //        "{player} vs {player}"

                String[] players = input.split(" vs ");
                String playerOne = players[0];
                String playerTwo = players[1];

                if (playerPool.containsKey(playerOne) && playerPool.containsKey(playerTwo)) {
                    // potential duel

                    Map<String, Integer> playerPosSkillMapOne = playerPool.get(playerOne);
                    Map<String, Integer> playerPosSkillMapTwo = playerPool.get(playerTwo);

                    int totalSkillPlayerOne = 0;
                    int totalSkillPlayerTwo = 0;

                    for (Map.Entry<String, Integer> playerOneEntry : playerPosSkillMapOne.entrySet()) {
                        String position = playerOneEntry.getKey();

                        if (playerPosSkillMapTwo.containsKey(position)) {
                            totalSkillPlayerOne += playerOneEntry.getValue();
                            totalSkillPlayerTwo += playerPosSkillMapTwo.get(position);
                        }
                    }

                    if (totalSkillPlayerOne > totalSkillPlayerTwo) {
                        // one beats two
                        playerPool.remove(playerTwo);
                    } else if (totalSkillPlayerTwo > totalSkillPlayerOne) {
                        // two beats one
                        playerPool.remove(playerOne);
                    }
                }
            }

            input = sc.nextLine();
        }

        // print players order by rules
        Map<String, Integer> playerTotalSkillMap = new LinkedHashMap<>();

        for (Map.Entry<String, Map<String, Integer>> playerEntry : playerPool.entrySet()) {

            String player = playerEntry.getKey();
            Map<String, Integer> positionPointsMap = playerEntry.getValue();

            playerTotalSkillMap.put(player, positionPointsMap.entrySet().stream()
                    .mapToInt(s -> s.getValue()).sum());
        }

        List<Map.Entry<String, Integer>> playerTotalsList = playerTotalSkillMap.entrySet().stream()
                .sorted((p1,p2) -> {

                    int result = p2.getValue().compareTo(p1.getValue());

                    return result == 0 ? p1.getKey().compareTo(p2.getKey()): result;
                })
                .collect(Collectors.toList());

        for (int i = 0; i < playerTotalsList.size(); i++) {
            Map.Entry<String, Integer> playerInfo = playerTotalsList.get(i);

            System.out.println(playerInfo.getKey()+": "+playerInfo.getValue()+" skill");

            Map<String, Integer> playerSkills = playerPool.get(playerInfo.getKey());

            playerSkills.entrySet().stream()
                    .sorted((p1,p2) -> {
                        int result = p2.getValue().compareTo(p1.getValue());

                        return result == 0 ? p1.getKey().compareTo(p2.getKey()): result;
                    })
                    .map(p -> String.format("- %s <::> %d", p.getKey(), p.getValue()))
                    .forEach(System.out::println);
        }



    }

    public static void addNewPlayer(Map<String, Map<String, Integer>> playerPool, String[] playerData) {

        String player = playerData[0];
        String position = playerData[1];
        Integer skill = Integer.parseInt(playerData[2]);

        if (!playerPool.containsKey(player)) {

            playerPool.put(player, new LinkedHashMap<>());
        }

        if (!playerPool.get(player).containsKey(position)) {
            playerPool.get(player).put(position, skill);
        }

        if (playerPool.get(player).get(position) < skill) {
            playerPool.get(player).put(position, skill);
        }

    }
}
