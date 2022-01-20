package com.dido.lab;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {

    public static void main(String[] args) {
        RandomArrayList randomArrayList = new RandomArrayList();

        randomArrayList.add(2);
        randomArrayList.add("bla");
        randomArrayList.add("bla1");

        System.out.println(randomArrayList.getRandomElement());
    }

    public Object getRandomElement() {
        int randIdx = new Random().nextInt(super.size());

        Object element = super.remove(randIdx);

        return element;
    }
}
