package com.dido.lab;

import java.util.Scanner;

public class GreaterOfTwo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();

        if (type.equalsIgnoreCase("int")) {
            int first = Integer.parseInt(sc.nextLine());
            int second = Integer.parseInt(sc.nextLine());

            System.out.println(getMax(first,second));
        } else if (type.equalsIgnoreCase("char")) {
            char first = sc.nextLine().charAt(0);
            char second = sc.nextLine().charAt(0);

            System.out.println(getMax(first,second));
        } else if (type.equalsIgnoreCase("string")) {
            String first = sc.nextLine();
            String second = sc.nextLine();

            System.out.println(getMax(first,second));
        }
    }

    public static int getMax(int firstNum, int secondNum) {

        if (firstNum > secondNum) {
            return firstNum;
        }

        return secondNum;

    }

    public static char getMax(char first, char second) {
        if (first > second) {
            return first;
        }

        return second;
    }

    public static String getMax(String first, String second) {

        if (first.compareTo(second) > 0) {
            return first;
        }

        return second;
    }
}
