package com.dido.exercise;

import java.util.Scanner;
// should be optimized ...

public class Triple<T,P,F> {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // •	The first one is holding a name, an address and a town. Format of the input:
        //<<first name> <last name>> <address> <town>
        String[] personData = sc.nextLine().split("\\s");
        String name = personData[0] + " "+personData[1];
        String address = personData[2];
        String town = personData[3];
        Triple<String, String, String> tripleOne = new Triple<>(name,address,town);
        //•	The second line is holding a name, beer liters, and a Boolean variable - drunk or not.
        // Format:
        //<name> <liters of beer> <drunk or not>
        personData = sc.nextLine().split("\\s");
        name = personData[0];
        Integer liters = Integer.parseInt(personData[1]);
        Boolean drunk = "drunk".equals(personData[2]) ? true : false;
        Triple<String, Integer, Boolean> tripleTwo = new Triple<>(name,liters,drunk);
        //•	The last line will hold a name, a bank balance double) and a bank name. Format:
        //<name> <account balance> <bank name>
        personData = sc.nextLine().split("\\s");
        name = personData[0];
        Double balance = Double.parseDouble(personData[1]);
        String bank = personData[2];
        Triple<String, Double, String> tripleThree = new Triple<>(name,balance,bank);

        System.out.println(tripleOne);
        System.out.println(tripleTwo);
        System.out.println(tripleThree);


    }

    private T itemOne;
    private P itemTwo;
    private F itemThree;

    public Triple(T itemOne, P itemTwo, F itemThree) {
        this.itemOne = itemOne;
        this.itemTwo = itemTwo;
        this.itemThree = itemThree;
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

    public F getItemThree() {
        return itemThree;
    }

    public void setItemThree(F itemThree) {
        this.itemThree = itemThree;
    }

    @Override
    public String toString() {
        return itemOne + " -> "+itemTwo + " -> "+itemThree;
    }
}
