package com.dido.more;

import java.util.Scanner;

public class DataTypes {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String type = sc.nextLine();
        if (type.equalsIgnoreCase("int")) {
            int n = Integer.parseInt(sc.nextLine());
            transformAndPrint(n);
        } else if (type.equalsIgnoreCase("real")) {
            double n = Double.parseDouble(sc.nextLine());
            transformAndPrint(n);
        } else if (type.equalsIgnoreCase("string")) {
            String thing = sc.nextLine();
            transformAndPrint(thing);
        }
    }

    public static void transformAndPrint(int n) {
        System.out.printf("%d",(n * 2));
    }

    public static void transformAndPrint(double n) {
        System.out.printf("%.2f",(n * 1.5));
    }

    public static void transformAndPrint(String n) {
        System.out.printf("$%s$",n);
    }
}
