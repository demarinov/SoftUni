package com.dido.lab;

import java.util.Scanner;

public class BitDestroyer {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int p = Integer.parseInt(sc.nextLine());

        System.out.println(destroyBit(n,p));


    }

    public static int destroyBit(int n, int p) {

        int shiftInvertOne = ~(1 << p);

        return (n & shiftInvertOne);
    }
}
