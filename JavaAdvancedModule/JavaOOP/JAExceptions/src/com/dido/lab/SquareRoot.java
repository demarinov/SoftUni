package com.dido.lab;

import java.util.Scanner;

public class SquareRoot {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);



        try {
            int n = Integer.parseInt(sc.nextLine());
            double result = Math.sqrt(n);
            System.out.println(result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
        } finally {
            System.out.println("Good bye");
        }
    }
}
