package com.dido.exercise;

import java.util.Scanner;
// should be optimized for judge...
public class Tuple<T,P> {

    private T itemOne;
    private P itemTwo;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String[] personData = sc.nextLine().split("\\s");
        String name = personData[0] + " "+personData[1];
        String address = personData[2];
        Tuple<String, String> tuple = new Tuple<>(name, address);

        personData = sc.nextLine().split("\\s");

        name = personData[0];
        Integer beerAmount = Integer.parseInt(personData[1]);
        Tuple<String, Integer> tuple2 = new Tuple<>(name, beerAmount);

        String[] numbers = sc.nextLine().split("\\s");

        Integer num1 = Integer.parseInt(numbers[0]);
        Double num2 = Double.parseDouble(numbers[1]);

        Tuple<Integer, Double> tuple3 = new Tuple<>(num1, num2);

        System.out.println(tuple);
        System.out.println(tuple2);
        System.out.println(tuple3);

    }

    public Tuple(T item1, P item2) {
        this.itemOne = item1;
        this.itemTwo = item2;
    }


    public T getItemOne() {
        return itemOne;
    }

    public void setItemOne(T itemOne) {
        this.itemOne = itemOne;
    }

    public P getItemTwo() {
        return itemTwo;
    }

    public void setItemTwo(P itemTwo) {
        this.itemTwo = itemTwo;
    }

    @Override
    public String toString() {
        return itemOne + " -> " + itemTwo;
    }
}
