package com.dido.lab;

import java.util.Scanner;

public class Calculations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String op = sc.nextLine();
        int a = Integer.parseInt(sc.nextLine());
        int b = Integer.parseInt(sc.nextLine());

        // ("add", "multiply", "subtract", "divide")
        if (op.equals("add")) {
            printAddResult(a,b);
        } else if (op.equals("multiply")) {
            printMultiplyResult(a,b);
        } else if (op.equals("subtract")) {
            printSubtractReuslt(a,b);
        } else if (op.equals("divide")) {
            printDivideResult(a,b);
        }


    }

    public static void printAddResult(int a,int b) {
        System.out.println(a+b);
    }

    public static void printMultiplyResult(int a, int b) {
        System.out.println(a * b);
    }

    public static void printSubtractReuslt(int a, int b) {

        System.out.println(a-b);
    }

    public static void printDivideResult(int a, int b) {
        if (b > 0) {
            System.out.println(a / b);
        }
    }
}
