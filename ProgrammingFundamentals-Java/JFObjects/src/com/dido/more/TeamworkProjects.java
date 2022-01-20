package com.dido.more;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeamworkProjects {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int countTeams = Integer.parseInt(sc.nextLine());

        if (countTeams <= 0) {
            return;
        }

        List<Creator> creators = new ArrayList<>();
        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < countTeams; i++) {

            String command = sc.nextLine();
            String[] creatorTeamData = command.split("-");

            if (creatorTeamData.length > 0) {
                applyCreateTeamRules(creatorTeamData, creators, teams);
            }

        }

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("end of assignment")) {



//            •	If user tries to create a team more than once a message should be displayed:
//            -	"Team {teamName} was already created!"
//•	Creator of a team cannot create another team - message should be thrown:
//            -	"{user} cannot create another team!"
//•	If user tries to join currently non-existing team a message should be displayed:
//            -	"Team {teamName} does not exist!"
//•	Member of a team cannot join another team - message should be thrown:
//            -	"Member {user} cannot join team {team Name}!"

            String[] memberTeamData = command.split("->");

            if (memberTeamData.length > 0) {
                applyTeamMemberRules(memberTeamData, teams);
            }

            command = sc.nextLine();
        }

        List<Team> disbandedTeams = new ArrayList<>();

        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getMembers().size() == 1) {
                disbandedTeams.add(team);
                teams.remove(team);
                i--;
            }
        }

        printTeams(teams);
        printDisbandedTeams(disbandedTeams);

    }

    public static void printDisbandedTeams(List<Team> teams) {

        Collections.sort(teams, (o1,o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println("Teams to disband:");
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(team.getName());
        }
    }

    public static void printTeams(List<Team> teams) {

        Collections.sort(teams, (o1,o2) -> {
                int result = Integer.valueOf(o2.getMembers().size())
                        .compareTo(Integer.valueOf(o1.getMembers().size()));
               return result == 0 ? o1.getName().compareTo(o2.getName()) : result; });
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            System.out.println(team.getName());

            List<Member> members = team.getMembers().stream()
                    .sorted((m1,m2) -> m1.getName().compareTo(m2.getName()))
                    .collect(Collectors.toList());
            System.out.println("- "+team.getCreator().getName());
            for (int j = 0; j < members.size(); j++) {
                if (!team.getCreator().getName().equals(members.get(j).getName())) {
                    System.out.println("-- " + members.get(j).getName());
                }
            }
        }
    }

    public static void applyTeamMemberRules(String[] memberTeamData, List<Team> teams) {
//•	If user tries to join currently non-existing team a message should be displayed:
//            -	"Team {teamName} does not exist!"
//•	Member of a team cannot join another team - message should be thrown:
//            -	"Member {user} cannot join team {team Name}!"
        String memberName = memberTeamData[0];
        String teamName  =memberTeamData[1];

        boolean teamExists = false;
        for (int i = 0; i < teams.size(); i++) {
            Team team = teams.get(i);
            if (team.getName().equals(teamName)) {
                teamExists = true;
                break;
            }
        }

        if (!teamExists) {
            System.out.println("Team "+teamName+" does not exist!");
            return;
        }

        for (int i = 0; i < teams.size(); i++) {

            Team team = teams.get(i);

            List<Member> members = team.getMembers();
            String currentTeam = "";
            boolean joinCurrentTeam = true;
            Creator creator = team.getCreator();

            for (int j = 0; j < members.size(); j++) {
                Member member = members.get(j);


                if (member.getName().equals(memberName)
                        || creator.getName().equals(memberName)) {

                    currentTeam = team.getName();
                    joinCurrentTeam = false;
                    break;
                }
            }

            if (!joinCurrentTeam) {
                System.out.println("Member "+memberName+" cannot join team "+teamName+"!");
                return;
            }
        }

        Member member = new Member(memberName);
        Team team = getTeamByName(teams, teamName);
        team.getMembers().add(member);


    }

    public static void applyCreateTeamRules(String[] creatorTeamData,
                                            List<Creator> creators, List<Team> teams) {

//        •	If user tries to create a team more than once a message should be displayed:
//            -	"Team {teamName} was already created!"
//•	Creator of a team cannot create another team - message should be thrown:
//            -	"{user} cannot create another team!"

        String teamName = creatorTeamData[1];
        boolean isTeamPresent = findTeamByName(teams, teamName);

        if (isTeamPresent) {
            System.out.println("Team "+teamName+" was already created!");
            return;
        }

        String creatorName = creatorTeamData[0];
        boolean hasTeamCreator = findTeamCreator(creators, creatorName);

        if (hasTeamCreator) {
            System.out.println(creatorName+" cannot create another team!");
            return;
        }

        List<Member> members = new ArrayList<>();
        Creator creator = new Creator(creatorName);
        Team team = new Team(teamName, creator, members);
        creator.setTeam(team);
        teams.add(team);
        Member member = new Member(creatorName);
        members.add(member);
        creators.add(creator);
        System.out.println("Team "+teamName+" has been created by "+creatorName+"!");
    }

    public static boolean findTeamCreator(List<Creator> creators, String creatorName) {

        for (int i = 0; i < creators.size(); i++) {

            Creator creator = creators.get(i);

            if (creator.getName().equals(creatorName)) {
                return true;
            }
        }

        return false;
    }

    public static boolean findTeamByName(List<Team> teams, String teamName) {

        for (int i = 0; i < teams.size(); i++) {

            Team team = teams.get(i);
            if (team.getName().equals(teamName)) {
                return true;
            }
        }

        return false;
    }
    public static Team getTeamByName(List<Team> teams, String teamName) {

        for (int i = 0; i < teams.size(); i++) {

            Team team = teams.get(i);
            if (team.getName().equals(teamName)) {
                return team;
            }
        }

        return null;
    }

}

class Creator {
    private String name;
    private Team team;

    public Creator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Team {

    private String name;
    private Creator creator;
    private List<Member> members;

    public Team(String name, Creator creator, List<Member> members) {
        this.name = name;
        this.creator = creator;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", creator=" + creator +
                ", members=" + members +
                '}';
    }
}
