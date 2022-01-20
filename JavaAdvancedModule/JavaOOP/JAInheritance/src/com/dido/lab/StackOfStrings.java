package com.dido.lab;

import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {

    public List<String> data;

    public StackOfStrings() {
        data = new ArrayList<>();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public static void main(String[] args) {

        StackOfStrings stackOfStrings = new StackOfStrings();

        stackOfStrings.push("Buddy");
        stackOfStrings.push("Bob");
        stackOfStrings.push("Bran");

        System.out.println(stackOfStrings.isEmpty());
        System.out.println(stackOfStrings.peek());
        System.out.println(stackOfStrings.pop());
        System.out.println(stackOfStrings.pop());
        System.out.println(stackOfStrings.pop());
    }

    public void push(String element) {
        data.add(element);
    }

    public String pop() {

        return data.remove(data.size()-1);
    }

    public String peek() {

        return data.get(data.size()-1);
    }
}
