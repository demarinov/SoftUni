package com.dido.lab;

import java.util.Scanner;

public class MathOp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double firstNum = Double.parseDouble(sc.nextLine());
        String operation = sc.nextLine();
        Double secondNum = Double.parseDouble(sc.nextLine());

        System.out.printf("%.0f",calculate(firstNum,operation,secondNum));
    }

    public static double calculate(double first, String op, double second) {

        double result = 0.0d;
        switch(op) {
            case "*":
                result = first * second;
                break;
            case "/":
                if (second > 0) {
                    result = first / second;
                }
                break;
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            default:
                break;
        }

        return result;
    }
}
