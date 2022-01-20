package com.dido.lab;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class SimpleCalculator {

    public static void main(String[] args) {
        // addition and subtraction
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split("\\s+");

        ArrayDeque<String> stack = new ArrayDeque<>();
        Collections.addAll(stack, tokens);

        while(stack.size() > 1) {

            Integer numOne = Integer.parseInt(stack.pop());
            String op = stack.pop();
            Integer numTwo = Integer.parseInt(stack.pop());


            switch(op) {

                case "+":
                    stack.push(String.format("%s",numOne+numTwo));
                    break;
                case "-":
                    stack.push(String.format("%s",numOne-numTwo));
                    break;
                default:
                    break;
            }
        }

        System.out.println(stack.pop());
    }
}
