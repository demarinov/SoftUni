package com.dido.more;

import java.util.Scanner;

public class PipesInPool {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = Integer.parseInt(sc.nextLine());
        int p1 = Integer.parseInt(sc.nextLine());
        int p2 = Integer.parseInt(sc.nextLine());
        double h = Double.parseDouble(sc.nextLine());

        double totalDebit = p1 * h + p2 * h;

        if (totalDebit > v) {
            System.out.printf("For %.2f hours the pool overflows with %.2f liters.", h, (totalDebit - v));
        } else {
            System.out.printf("The pool is %.2f%% full. Pipe 1: %.2f%%. Pipe 2: %.2f%%."
                    , (100.0 * totalDebit / v), (p1 * 100.0 * h / totalDebit), (p2 * 100.0 * h / totalDebit));
        }
    }
}
