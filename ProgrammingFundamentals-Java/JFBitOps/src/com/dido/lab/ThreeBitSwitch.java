package com.dido.lab;

import java.util.Scanner;

public class ThreeBitSwitch {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());


        System.out.println(switchThreeBits(num, p));

    }

    public static int switchThreeBits(int n, int p) {

        // 7 has 3 bits on 111
        int mask = 7 << p;

        return (n ^ mask);
    }
}
