package guild;

import java.util.ArrayList;
import java.util.List;

public class GuildProject {

    public static void main(String[] args) {
        //Initialize the repository (guild)
        Guild guild = new Guild("Weekend Raiders", 20);
        //Initialize entity
        Player player = new Player("Mark", "Rogue");
        //Print player
        System.out.println(player);
        //Player Mark: Rogue
        //Rank: Trial
        //Description: n/a

        //Add player
        guild.addPlayer(player);
        System.out.println(guild.count()); //1
        System.out.println(guild.removePlayer("Gosho")); //false

        Player firstPlayer = new Player("Pep", "Warrior");
        Player secondPlayer = new Player("Lizzy", "Priest");
        Player thirdPlayer = new Player("Mike", "Rogue");
        Player fourthPlayer = new Player("Marlin", "Mage");

        //Add description to player
        secondPlayer.setDescription("Best healer EU");

        //Add players
        guild.addPlayer(firstPlayer);
        guild.addPlayer(secondPlayer);
        guild.addPlayer(thirdPlayer);
        guild.addPlayer(fourthPlayer);

        //Promote player
        guild.promotePlayer("Lizzy");

        //Remove Player
        System.out.println(guild.removePlayer("Pep")); //true

        Player[] kickedPlayers = guild.kickPlayersByClass("Rogue");
        for (Player kickedPlayer : kickedPlayers) {
            System.out.print(kickedPlayer.getName() + " ");
        }
        //Mark Mike

        System.out.println(guild.report());
        //Players in the guild: Weekend Raiders:
        //Player Lizzy: Priest
        //Rank: Member
        //Description: Best healer EU
        //Player Marlin: Mage
        //Rank: Trial
        //Description: n/a

    }

    static class Guild {

        private String name;
        private int capacity;

        private List<Player> playerList;

        public Guild(String name, int capacity) {
            this.name = name;
            this.capacity = capacity;
            this.playerList = new ArrayList<>();
        }

        public void addPlayer(Player player) {

            if (playerList.size() < capacity) {
                playerList.add(player);
            }
        }

        public boolean removePlayer(String name) {

            return playerList.removeIf(p -> name.equals(p.getName()));
        }

        public void promotePlayer(String name) {
            Player player = playerList.stream().filter(p -> name.equals(p.getName()))
                    .findFirst().orElse(null);
            if (player != null && !"Member".equals(player.getRank())) {
                player.setRank("Member");
            }
        }

        public void demotePlayer(String name) {
            Player player = playerList.stream().filter(p -> name.equals(p.getName()))
                    .findFirst().orElse(null);
            if (player != null && !"Trial".equals(player.getRank())) {
                player.setRank("Trial");
            }
        }

        public Player[] kickPlayersByClass(String clazz) {

            Player[] players = playerList.stream().filter(p -> clazz.equals(p.getClazz()))
                    .toArray(Player[]::new);

            playerList.removeIf(p -> clazz.equals(p.getClazz()));
            return players;
        }

        public int count() {
            return playerList.size();
        }

        public String report() {
            String playersOutput = playerList.stream().map(p -> String.format("%s%n",p))
                    .reduce("",String::concat);
            return String.format("Players in the guild: %s:%n" +
                    "%s",name,playersOutput);
        }
    }

    static class Player {

        private String name;
        private String clazz;
        private String rank;
        private String description;

        public Player(String name, String clazz) {
            this.name = name;
            this.clazz = clazz;
            this.rank = "Trial";
            this.description = "n/a";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClazz() {
            return clazz;
        }

        public void setClazz(String clazz) {
            this.clazz = clazz;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
//            "Player {name}: {clazz}
//            Rank: {rank}
//            Description: {description}"


            return String.format("Player %s: %s%n" +
                    "Rank: %s%n" +
                    "Description: %s",name,clazz,rank,description);
        }
    }
}
