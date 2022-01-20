package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;

public class BrowserHistoryUpgrade {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayDeque<String> browserStack = new ArrayDeque<>();
        String line = sc.nextLine();

        Deque<String> forwardStack = new ArrayDeque<>();
        String current = "";

        while(!line.equals("Home")) {

            if (line.equals("back")) {
                if (!browserStack.isEmpty()) {

                    if (!current.isEmpty()) {
                        forwardStack.push(current);
                    }
                    current = browserStack.pop();
                }  else {
                    System.out.println("no previous URLs");
                    line = sc.nextLine();
                    continue;
                }
            } else if (line.equals("forward")) {

                if (!forwardStack.isEmpty()) {
                    if(!current.isEmpty()) {
                        browserStack.push(current);
                    }
                    current = forwardStack.pop();
                } else {
                    System.out.println("no next URLs");
                    line = sc.nextLine();
                    continue;
                }
            } else {
                if (!current.isEmpty()) {
                    browserStack.push(current);
                }
                if (!forwardStack.isEmpty()) {
                    forwardStack.clear();
                }
                current = line;
            }

            System.out.println(current);
            line = sc.nextLine();
        }

    }
}
