package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here

        System.out.printf("%.2f %n",2.2345);
        double roundedValue =1.0 * Math.round((0.06d - 0.05d)*100)/100;
        System.out.println(roundedValue);
        System.out.println(Math.floor(roundedValue * 100)/100);
    }
}
