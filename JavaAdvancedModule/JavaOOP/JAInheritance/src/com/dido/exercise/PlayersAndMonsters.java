package com.dido.exercise;

import java.util.Scanner;

public class PlayersAndMonsters {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Wizard wizard = new Wizard("Mendo",1);

        System.out.println(wizard);
    }
}

class Hero {

    private String userName;
    private int level;

    public Hero(String userName, int level) {
        this.userName = userName;
        this.level = level;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return String.format("Type: %s Username: %s Level: %d"
                , this.getClass().getName(), this.getUserName(), this.level);
    }
}

class BladeKnight extends Knight {
    public BladeKnight(String userName, int level) {
        super(userName, level);
    }
}

class SoulMaster extends DarkWizard {
    public SoulMaster(String userName, int level) {
        super(userName, level);
    }
}

class DarkKnight extends Knight {
    public DarkKnight(String userName, int level) {
        super(userName, level);
    }
}

class DarkWizard extends Wizard {
    public DarkWizard(String userName, int level) {
        super(userName, level);
    }
}

class MuseElf extends Elf {
    public MuseElf(String userName, int level) {
        super(userName, level);
    }
}

class Knight extends Hero {
    public Knight(String userName, int level) {
        super(userName, level);
    }
}

class Wizard extends Hero {
    public Wizard(String userName, int level) {
        super(userName, level);
    }
}

class Elf extends Hero {
    public Elf(String userName, int level) {
        super(userName, level);
    }
}


