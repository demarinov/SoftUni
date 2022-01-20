package com.dido.exercise;

import java.util.*;

public class InfixToPostfix {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tokens = sc.nextLine().split("\\s");

        Queue<String> outputQueue = new ArrayDeque<>();
        Deque<String> operatorStack = new ArrayDeque<>();
        Map<String, Integer> precedenceMap = new LinkedHashMap<>();

        precedenceMap.putIfAbsent("+", 1);
        precedenceMap.putIfAbsent("-", 1);
        precedenceMap.putIfAbsent("*", 2);
        precedenceMap.putIfAbsent("/", 2);
        precedenceMap.putIfAbsent("(", 3);
        precedenceMap.putIfAbsent(")", 3);

        for (int i = 0; i < tokens.length; i++) {

            String token = tokens[i];

            if (!precedenceMap.containsKey(token)) {
                // either valid digits or variables or some other invalid chars

                // if valid
                outputQueue.offer(token);
            } else if (!token.equals("(") && !token.equals(")")) {
                // operator not parenthesis ...
                String stackOp = operatorStack.peek();
                while (stackOp != null
                        && precedenceMap.get(stackOp) >= precedenceMap.get(token)
                        && !stackOp.equals("(")) {
                    stackOp = operatorStack.pop();
                    outputQueue.offer(stackOp);

                    stackOp = operatorStack.peek();
                }

                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {

                String stackOp = operatorStack.peek();

                while(!stackOp.equals("(")) {

                    stackOp = operatorStack.pop();
                    outputQueue.offer(stackOp);
                    stackOp = operatorStack.peek();
                }

                if (stackOp.equals("(")) {
                    // pop and discard
                    operatorStack.pop();
                }
            }
        }

        while(!operatorStack.isEmpty()) {
            String stackOp = operatorStack.pop();

            outputQueue.offer(stackOp);
        }

        outputQueue.stream().map(t -> String.format("%s ",t))
                .forEach(System.out::print);
    }
}
