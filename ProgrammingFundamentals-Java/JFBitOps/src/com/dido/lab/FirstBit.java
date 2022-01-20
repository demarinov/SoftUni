package com.dido.lab;

import java.util.Scanner;

public class FirstBit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        printFirstBit(num);
    }

    public static void printFirstBit(int num) {
        int bitAtPosOne = 0;

        int shiftedNum = num >> 1;
        bitAtPosOne = shiftedNum & 1;

        System.out.println(bitAtPosOne);
    }
}
