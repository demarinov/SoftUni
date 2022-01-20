package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Scanner;

public class BrowserHistory {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> browserStack = new ArrayDeque<>();
        String line = sc.nextLine();

        String current = "";

        while(!line.equalsIgnoreCase("home")) {

            if (line.equalsIgnoreCase("back")) {
                if (!browserStack.isEmpty()) {
                    current = browserStack.pop();
                } else {
                    System.out.println("no previous URLs");
                    line = sc.nextLine();
                    continue;
                }
            } else {
                if (!current.isEmpty()) {
                    browserStack.push(current);

                }
                current = line;
            }

            System.out.println(current);
            line = sc.nextLine();
        }

    }
}
