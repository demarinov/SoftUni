package guild;

import java.util.ArrayList;
import java.util.List;

public class Guild {

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