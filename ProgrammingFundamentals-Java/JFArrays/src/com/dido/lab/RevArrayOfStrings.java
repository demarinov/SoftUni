package com.dido.lab;

import java.util.Scanner;

public class RevArrayOfStrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] strs = sc.nextLine().split(" ");

        for (int i = 0; i < strs.length/2; i++) {
            
            int nextLastIdx = strs.length - 1 - i;
            
            // swap elems
            String elemBck = strs[i];
            strs[i] = strs[nextLastIdx];
            strs[nextLastIdx] = elemBck;
        }

        for (int i = 0; i < strs.length; i++) {
            System.out.printf("%s ",strs[i]);
        }
    }
}
