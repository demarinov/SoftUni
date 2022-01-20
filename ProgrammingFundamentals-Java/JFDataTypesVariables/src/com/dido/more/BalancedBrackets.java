package com.dido.more;

import java.util.Scanner;

public class BalancedBrackets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        boolean isBalanced = true;
        char prevBracket = '\0';
        boolean fbMet = false;
        int countBrackets = 0;

        for (int i = 1; i <= n; i++) {

            String str = sc.nextLine();

            if ((str.charAt(0) == '(' || str.charAt(0) == ')') && isBalanced) {

                char curBracket = str.charAt(0);
                countBrackets++;

                if (!fbMet) {
                    fbMet = true;
                    prevBracket = curBracket;
                    continue;
                }


                if (prevBracket == '(' && curBracket == ')') {
                    isBalanced = true;
                    fbMet = false;
                } else {
                    isBalanced = false;
                }

                prevBracket = curBracket;

            } else {
                // get the non-bracket string if needed ...
            }

        }

        System.out.println(isBalanced && countBrackets > 0 && !fbMet ? "BALANCED" : "UNBALANCED");
    }
}
