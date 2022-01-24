package com.lab;

import java.util.Scanner;

public class CreateProjects {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        int projectNum = Integer.parseInt(scan.nextLine());

        System.out.println("The architect " + name + " will need " +
                (projectNum * 3) + " hours to complete "
                + projectNum + " project/s.");

    }
}
