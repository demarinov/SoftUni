package com.company.lab;

import javax.print.DocFlavor;
import java.util.Scanner;

public class Moving {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // one box - 1m. x 1m. x 1m
        int width = Integer.parseInt(sc.nextLine());
        int length = Integer.parseInt(sc.nextLine());
        int height = Integer.parseInt(sc.nextLine());
        int space = height * width * length;
        int freeSpace = space;

        String input = sc.nextLine();

        while(!input.equals("Done")) {

            int box = Integer.parseInt(input);
            freeSpace -= box;

            if (freeSpace < 0) {
                break;
            }

            input = sc.nextLine();
        }

        if (input.equals("Done")) {
            System.out.println(freeSpace +
                    " Cubic meters left.");
        } else {
            System.out.println("No more free space! You need " +
                    Math.abs(freeSpace) +
                    " Cubic meters more.");
        }
    }
}
