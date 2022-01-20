package com.dido.exercise;

import java.util.Scanner;

public class GenericBoxInt {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            Integer input = Integer.parseInt(sc.nextLine());
            Box<Integer> box = new Box<>();
            box.setValue(input);

            System.out.println(box);
        }

    }

    static class Box<T> {

        private T value;

        public Box(){

        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.getClass().getTypeName() + ": "+value;
        }
    }
}
