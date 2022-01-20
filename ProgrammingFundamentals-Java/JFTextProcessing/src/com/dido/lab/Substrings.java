package com.dido.lab;

import java.util.Scanner;

public class Substrings {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String s1 = sc.nextLine();
        String s2 = sc.nextLine();

        int idx = s2.indexOf(s1);

        while(idx >= 0) {

            s2 = s2.replace(s1,"");

            idx = s2.indexOf(s1);
        }

        System.out.println(s2);
    }
}
