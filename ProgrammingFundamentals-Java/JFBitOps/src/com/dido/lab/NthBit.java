package com.dido.lab;

import java.util.Scanner;

public class NthBit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        int pos = Integer.parseInt(sc.nextLine());

        printNthBit(num,pos);
    }

    public static void printNthBit(int num, int pos) {

        int shiftedNum = num >> pos;

        int bitNthPos = shiftedNum & 1;

        System.out.println(bitNthPos);

    }
}
