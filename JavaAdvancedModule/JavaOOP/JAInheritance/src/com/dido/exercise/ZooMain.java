package com.dido.exercise;

import java.util.Scanner;

public class ZooMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Bear bear = new Bear("Grizzla");
        Snake snake = new Snake("Roda");

        System.out.println(bear.getName());
        System.out.println(snake.getName());
    }
}

class Bear extends Mammal {
    public Bear(String name) {
        super(name);
    }
}

class Gorilla extends Mammal {
    public Gorilla(String name) {
        super(name);
    }
}

class Mammal extends Zoo {
    public Mammal(String name) {
        super(name);
    }
}

class Snake extends Reptile {
    public Snake(String name) {
        super(name);
    }
}

class Lizard extends Reptile {
    public Lizard(String name) {
        super(name);
    }
}

class Reptile extends Zoo {

    public Reptile(String name) {
        super(name);
    }
}

class Zoo {
    private String name;

    public Zoo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


