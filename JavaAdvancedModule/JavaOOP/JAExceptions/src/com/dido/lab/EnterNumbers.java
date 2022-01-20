package com.dido.lab;

import java.util.Scanner;

public class EnterNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean isInvalid = true;

        while(isInvalid) {

            isInvalid = false;

            try {
                int start = Integer.parseInt(sc.nextLine());
                int end = Integer.parseInt(sc.nextLine());
                printNumber(start,end);
            } catch (NumberFormatException e) {
                isInvalid = true;
            }
        }
    }

    public static void printNumber(int start, int end) throws NumberFormatException {

        if (start >= end || start < 1 || end > 100) {
            throw new NumberFormatException("Invalid number");
        }

        for (int i = start; i < end; i++) {
            System.out.println(i);
        }
    }
}
