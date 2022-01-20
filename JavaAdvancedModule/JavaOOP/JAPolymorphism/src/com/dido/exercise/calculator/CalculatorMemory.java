package com.dido.exercise.calculator;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMemory {

    private static List<Integer> memoryItems = new ArrayList<>();


    public static void addToMemory(Integer num) {
        memoryItems.add(num);
    }

    public static Integer pullFromMemory() {

        Integer item = memoryItems.get(memoryItems.size()-1);
        memoryItems.remove(item);
        return item;
    }

}
