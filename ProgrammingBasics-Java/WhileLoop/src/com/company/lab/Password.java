package com.company.lab;

import java.util.Scanner;

public class Password {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String user = sc.nextLine();
        String pass = sc.nextLine();

        String input = sc.nextLine();
        while(!pass.equals(input)) {

            input = sc.nextLine();
        }

        System.out.println("Welcome "+user+"!");
    }
}
