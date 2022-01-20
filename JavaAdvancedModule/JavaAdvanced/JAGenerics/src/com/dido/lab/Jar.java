package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Deque;

public class Jar<T> {

    public static void main(String[] args) {

        Jar<Pickle> jars = new Jar();

        jars.add(new Pickle());
        jars.add(new Pickle());

        Pickle pickle = jars.remove();
    }

    Deque<T> elementList;

    public Jar() {
        this.elementList = new ArrayDeque<>();
    }

    static class Pickle{
        private String name;

        public Pickle(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public T remove() {

        return this.elementList.pop();
    }

    public void add(T element) {

        this.elementList.push(element);
    }
}
