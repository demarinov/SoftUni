package com.lab;

import java.util.Scanner;

public class ConcatenateData {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String fName = scan.nextLine();
        String lName = scan.nextLine();
        int age = Integer.parseInt(scan.nextLine());
        String city = scan.nextLine();

        System.out.println("You are " + fName + " " + lName + ", a "
                + age + "-years old person from " + city + ".");


    }
}
