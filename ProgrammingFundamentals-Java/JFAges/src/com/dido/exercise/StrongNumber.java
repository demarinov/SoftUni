package com.dido.exercise;

import java.util.Scanner;

public class StrongNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String num = sc.nextLine();
        int sum = 0;

        for (int i = 0; i <= num.length()-1; i++) {

            int numI = Integer.parseInt(String.format("%c",num.charAt(i)));
            int facNum = 1;

            for (int j = 1; j <= numI; j++) {
                facNum *= j;
            }

            sum += facNum;
        }


        if (sum == Integer.parseInt(num)) {
            System.out.printf("yes");
        } else {
            System.out.printf("no");
        }
    }
}
