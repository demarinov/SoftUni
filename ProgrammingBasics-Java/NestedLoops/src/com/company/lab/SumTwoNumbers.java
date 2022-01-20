package com.company.lab;

import java.util.Scanner;

public class SumTwoNumbers {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        int m = Integer.parseInt(sc.nextLine());
        int combinations = 0;
        boolean comboFound = false;
        int i1 = 0;
        int i2 = 0;

        for (int i = n1; i <=n2; i++) {
            for (int j = n1; j <=n2; j++) {

                combinations++;
                if ((i + j) == m) {
                    i1 = i;
                    i2 = j;
                    comboFound = true;
                    break;
                }
            }

            if (comboFound) {
                break;
            }

        }

        if (comboFound) {
            System.out.println("Combination N:" +
                    combinations + " ("+
                    i1 +
                    " + " +
                    i2 +
                    " = " +
                    m +
                    ")");
        } else {
            System.out.println(combinations +
                    " combinations - neither equals " +
                    m);
        }
    }
}
