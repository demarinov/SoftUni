package com.dido.exercise;

import java.util.*;

public class BalancedParentheses {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String brackets = sc.nextLine();

        boolean isBalanced = true;
        Deque<Character> openBracketsStack = new ArrayDeque<>();
        Map<Character, Character> bracketsMap = new LinkedHashMap();

        bracketsMap.put(')','(');
        bracketsMap.put('}','{');
        bracketsMap.put(']','[');

        if (brackets.length() <= 1) {
            isBalanced = false;
        }

        for (int i = 0; i < brackets.length(); i++) {

            char c = brackets.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                openBracketsStack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (!openBracketsStack.isEmpty()) {
                    char comparedChar = openBracketsStack.pop();

                    if (bracketsMap.get(c) != comparedChar) {
                        isBalanced = false;
                        break;
                    }
                } else {

                    isBalanced = false;
                    break;
                }
            }
        }

        if (isBalanced) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
