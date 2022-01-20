package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FootballTeamGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // •	If you receive a command to add a player to a missing team,
        // print "Team {team name} does not exist."
        //•	If you receive a command to show stats for a missing team,
        // print "Team {team name} does not exist."

        String input  =sc.nextLine();

        List<Team> teamList = new ArrayList<>();
        while(!"END".equals(input)) {

            String[] commandData= input.split(";");

            try {
                switch (commandData[0]) {

                    case "Team":
                        Team team = new Team(commandData[1]);
                        teamList.add(team);

                        break;
                    case "Add":
                        String teamName = commandData[1];
                        String playerName = commandData[2];
                        int endurance = Integer.parseInt(commandData[3]);
                        int sprint = Integer.parseInt(commandData[4]);
                        int dribble = Integer.parseInt(commandData[5]);
                        int passing = Integer.parseInt(commandData[6]);
                        int shooting = Integer.parseInt(commandData[7]);

                        Player player = new Player(playerName, endurance,sprint
                                , dribble, passing, shooting);
                        team = teamList.stream().filter(t -> teamName.equals(t.getName()))
                                .findFirst().orElse(null);

                        if (team == null) {
                            throw new IllegalArgumentException("Team "+teamName+" does not exist.");
                        }
                        team.addPlayer(player);
                        break;
                    case "Remove":
                        teamName = commandData[1];
                        playerName = commandData[2];
                        team = teamList.stream().filter(t -> teamName.equals(t.getName()))
                                .findFirst().orElse(null);

                        if (team != null) {
                            team.removePlayer(playerName);
                        }

                        break;
                    case "Rating":
                        teamName = commandData[1];
                        team = teamList.stream().filter(t -> teamName.equals(t.getName()))
                                .findFirst().orElse(null);

                        if (team == null) {
                            throw new IllegalArgumentException("Team "+teamName+" does not exist.");
                        }

                        System.out.println(team);
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
            }

            input = sc.nextLine();
        }


    }

    static class Player {
        private String name;
        private int endurance;
        private int sprint;
        private int dribble;
        private int passing;
        private int shooting;

        public Player(String name, int endurance, int sprint, int dribble, int passing, int shooting) {
            setName(name);
            setEndurance(endurance);
            setSprint(sprint);
            setDribble(dribble);
            setPassing(passing);
            setShooting(shooting);
        }

        public int getEndurance() {
            return endurance;
        }

        private void setEndurance(int endurance) {

            if (endurance < 0 || endurance > 100) {
                throw new IllegalArgumentException("Endurance should be between 0 and 100.");
            }

            this.endurance = endurance;
        }

        public int getSprint() {
            return sprint;
        }

        private void setSprint(int sprint) {

            if (sprint < 0 || sprint > 100) {
                throw new IllegalArgumentException("Sprint should be between 0 and 100.");
            }
            this.sprint = sprint;
        }

        public int getDribble() {
            return dribble;
        }

        private void setDribble(int dribble) {

            if (dribble < 0 || dribble > 100) {
                throw new IllegalArgumentException("Dribble should be between 0 and 100.");
            }

            this.dribble = dribble;
        }

        public int getPassing() {
            return passing;
        }

        private void setPassing(int passing) {

            if (passing < 0 || passing > 100) {
                throw new IllegalArgumentException("Dribble should be between 0 and 100.");
            }

            this.passing = passing;
        }

        public int getShooting() {
            return shooting;
        }

        private void setShooting(int shooting) {

            if (shooting < 0 || shooting > 100) {
                throw new IllegalArgumentException("Shooting should be between 0 and 100.");
            }
            this.shooting = shooting;
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {

            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("A name should not be empty.");
            }
            this.name = name;
        }

        public double overallSkillLevel() {

            double skill = 1.0 *
                    (getEndurance() + getDribble() + getPassing() + getSprint() + getShooting())/5;
            return skill;
        }
    }

    static class Team {

        private String name;
        private List<Player> playerList;

        public Team(String name) {
            this.name = name;
            this.playerList = new ArrayList<>();
        }

        public String getName() {
            return name;
        }

        private void setName(String name) {

            if (name.trim().isEmpty()) {
                throw new IllegalArgumentException("A name should not be empty.");
            }

            this.name = name;
        }

        public void addPlayer(Player player) {
            playerList.add(player);
        }

        public void removePlayer(String playerName) {

            Player player = playerList.stream().filter(p -> playerName.equals(p.getName()))
                    .findFirst().orElse(null);
            if (player == null) {
                throw new IllegalArgumentException("Player "+playerName+" is not in "
                        +this.getName()+" team.");
            }
            playerList.removeIf(p -> playerName.equals(p.getName()));
        }

        public void setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
        }

        public double getRating() {

            double rating = playerList.stream()
                    .mapToDouble(p -> p.overallSkillLevel()).sum() / playerList.size();
            return rating;
        }

        @Override
        public String toString() {
            return String.format("%s - %d",this.getName(), Math.round(this.getRating()));
        }
    }
}
