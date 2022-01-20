package com.dido.exercise;

import java.util.Scanner;

public class HeiganDance {

    public static Scanner sc = new Scanner(System.in);
    public static int[][] arena = new int[15][15];

    public static void main(String[] args) {

        double damageToHeigan = Double.parseDouble(sc.nextLine());

        double heiganPower = 3000000d;
        int playerPower = 18500;
        int playerPosA = arena.length / 2;
        int playerPosB = arena[0].length / 2;

        Player player = new Player();
        player.setPlayerPower(playerPower);
        player.setPosA(playerPosA);
        player.setPosB(playerPosB);

        String input = "";
        // player starts at center 7,7
        while (player.getPlayerPower() > 0 && heiganPower > 0) {

            cleanArena();
            // damage heigan
            heiganPower -= damageToHeigan;

            if (player.isActiveCloudSpell()) {
                player.setPlayerPower(player.getPlayerPower() - 3500);
                player.setActiveCloudSpell(false);

                if (player.getPlayerPower() < 0) {
                    break;
                }
            }

            if (heiganPower < 0) {
                break;
            }

            input = sc.nextLine();
            String[] inputData = input.split("\\s+");
            String spell = inputData[0];
            player.setCurrentSpell(spell);
            int rowDamage = Integer.parseInt(inputData[1]);
            int colDamage = Integer.parseInt(inputData[2]);

//            if (rowDamage < 0 || rowDamage >= arena.length
//                    || colDamage < 0 || colDamage >= arena[0].length) {
//                continue;
//            }

            // heigan can also hit outside the arena, at least one cell to be hit inside....
            // from this row col spread damage in all 4 directions
            // move player if spell reaches him - try in each of 4 directions
            // player cannot move the he is hit
            doDamageToArenaCellsV2(rowDamage, colDamage);

            if (arena[player.getPosA()][player.getPosB()] == -1) {
                moveOrDamagePlayer(player);
            }

        }

        if (heiganPower <= 0) {
            System.out.println("Heigan: Defeated!");
        } else {
            System.out.printf("Heigan: %.2f%n", heiganPower);
        }

        if (player.getPlayerPower() <= 0) {
            System.out.println("Player: Killed by " + player.getPrefix() + player.getCurrentSpell());
        } else {
            System.out.printf("Player: %d%n", player.getPlayerPower());
        }

        System.out.printf("Final position: %d, %d", player.getPosA(), player.getPosB());
    }

    public static void cleanArena() {

        for (int i = 0; i < arena.length; i++) {

            for (int j = 0; j < arena[i].length; j++) {

                arena[i][j] = 0;
            }
        }
    }

    private static boolean isRowValid(int startPlRow) {
        return startPlRow >= 0 && startPlRow < arena.length;
    }

    private static boolean isColValid(int startPlCol) {
        return startPlCol >= 0 && startPlCol < arena[0].length;
    }

    public static void moveOrDamagePlayer(Player player) {

        // try move up

        int upPos = player.getPosA() - 1;
        if (isRowValid(upPos) && arena[upPos][player.getPosB()] != -1) {
            player.setPosA(upPos);
            // player avoids damage
            return;
        }

        // try move right
        int rightPos = player.getPosB() + 1;
        if (isColValid(rightPos) && arena[player.getPosA()][rightPos] != -1) {
            player.setPosB(rightPos);
            // player avoids damage
            return;
        }

        // try move down
        int downPos = player.getPosA() + 1;
        if (isRowValid(downPos) && arena[downPos][player.getPosB()] != -1) {
            player.setPosA(downPos);
            // player avoids damage
            return;
        }

        // try move left
        int leftPos = player.getPosB() - 1;
        if (isColValid(leftPos) && arena[player.getPosA()][leftPos] != -1) {
            player.setPosB(leftPos);
            // player avoids damage
            return;
        }


        switch (player.getCurrentSpell()) {

            case "Cloud":
                player.setPlayerPower(player.getPlayerPower() - 3500);
                player.setActiveCloudSpell(true);
                break;
            case "Eruption":
                player.setPlayerPower(player.getPlayerPower() - 6000);
                break;
            default:
                throw new IllegalArgumentException("Invalid spell: " + player.getCurrentSpell());
        }


    }

    static class Player {

        private int playerPower;
        private int posA;
        private int posB;
        private String currentSpell;
        private boolean activeCloudSpell;
        private String prefix;

        public Player() {
        }

        public String getPrefix() {
            return prefix;
        }

        public boolean isActiveCloudSpell() {
            return activeCloudSpell;
        }

        public void setActiveCloudSpell(boolean activeCloudSpell) {
            this.activeCloudSpell = activeCloudSpell;
        }

        public String getCurrentSpell() {
            return currentSpell;
        }

        public void setCurrentSpell(String currentSpell) {
            if (currentSpell.equals("Cloud")) {
                this.prefix = "Plague ";
            } else {
                this.prefix = "";
            }
            this.currentSpell = currentSpell;
        }

        public int getPlayerPower() {
            return playerPower;
        }

        public void setPlayerPower(int playerPower) {
            this.playerPower = playerPower;
        }

        public int getPosA() {
            return posA;
        }

        public void setPosA(int posA) {
            this.posA = posA;
        }

        public int getPosB() {
            return posB;
        }

        public void setPosB(int posB) {
            this.posB = posB;
        }
    }

    public static void doDamageToArenaCellsV2(int rowDamage, int colDamage) {
        for (int i = rowDamage - 1; i <= rowDamage + 1; i++) {
            if (i >= 0 && i < arena.length) {
                for (int j = colDamage - 1; j <= colDamage + 1; j++) {
                    if (j >= 0 && j < arena[rowDamage].length) {
                        arena[i][j] = -1;
                    }
                }
            }
        }
    }

    public static void doDamageToArenaCells(int rowDamage, int colDamage) {

        // can be done with loop ....
        // damage init pos
        arena[rowDamage][colDamage] = -1;

        int currentCol = colDamage;
        // go left
        if (--currentCol >= 0) {

            arena[rowDamage][currentCol] = -1;
        }

        // go right
        currentCol = colDamage;
        if (++currentCol < arena[0].length) {
            arena[rowDamage][currentCol] = -1;
        }


        // go up
        int currentRow = rowDamage;
        if (--currentRow >= 0) {

            arena[currentRow][colDamage] = -1;
        }

        currentCol = colDamage - 1;
        // go left and up
        if (currentRow >= 0 && currentCol >= 0) {

            arena[currentRow][currentCol] = -1;
        }

        // go right up
        currentCol = colDamage + 1;
        if (currentRow >= 0 && currentCol < arena[0].length) {

            arena[currentRow][currentCol] = -1;
        }


        // go down
        currentRow = rowDamage;

        if (++currentRow < arena.length) {

            arena[currentRow][colDamage] = -1;
        }

        currentCol = colDamage + 1;
        // go right and down
        if (currentRow < arena.length && currentCol < arena[0].length) {

            arena[currentRow][currentCol] = -1;
        }

        currentCol = colDamage - 1;
        // go left down
        if (currentRow < arena.length && currentCol >= 0) {

            arena[currentRow][currentCol] = -1;
        }

    }
}
