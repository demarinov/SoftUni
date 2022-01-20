package com.dido.more;

import java.util.Scanner;

public class GradsToFahrenheit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double grads = Double.parseDouble(sc.nextLine());

        double faren = grads * 1.8 + 32;

        System.out.printf("%.2f",faren);
    }
}
