package com.dido.lab;

public class MathOperation {

    public static void main(String[] args) {

        MathOperation mathOperation = new MathOperation();

        System.out.println(mathOperation.add(2,2));
        System.out.println(mathOperation.add(3,3,3));
        System.out.println(mathOperation.add(4,4,4,4));
    }

    public int add(int one, int two) {

        return one + two;
    }

    public int add(int one, int two, int three) {

        return one + two + three;
    }

    public int add(int one, int two, int three, int four) {

        return one + two + three + four;
    }
}
