package com.company.exercise;

import java.util.Scanner;

public class OperationsNumbers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        String op = sc.nextLine();
        double res = 0.0d;
        boolean isEven = false;

        switch(op) {

            case "+":
                res = n1 + n2;
                isEven = res % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s",n1,op,n2,res,(isEven ? "even" : "odd"));
                break;

            case "-":
                res = n1 - n2;
                isEven = res % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s",n1,op,n2,res,(isEven ? "even" : "odd"));
                break;

            case "*":
                res = n1 * n2;
                isEven = res % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s",n1,op,n2,res,(isEven ? "even" : "odd"));
                break;

            case "/":

                if (n2 == 0) {
                    System.out.println("Cannot divide "+n1+" by zero");
                } else {
                    res = n1 / (n2 * 1.0d);
                    System.out.printf("%d %s %d = %.2f", n1, op, n2, res);
                }
                break;

            case "%":

                if (n2 == 0) {
                    System.out.println("Cannot divide "+n1+" by zero");
                } else {
                    res = n1 % n2;
                    System.out.printf("%d %s %d = %.0f", n1, op, n2, res);
                }
                break;

            default:
                break;
        }


    }
}
