package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class MatchingBrackets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String expression = sc.nextLine();

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {

            char c = expression.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int startIndex = stack.pop();

                String contents = expression.substring(startIndex, i+1);
                System.out.println(contents);
            }
        }
    }
}
