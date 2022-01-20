package com.dido.more;

import java.util.Scanner;

public class WeatherForecast2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double grads = Double.parseDouble(sc.nextLine());

        if (grads >= 26 && grads <= 35) {
            System.out.println("Hot");
        } else if (grads >= 20.1 && grads <= 25.9) {
            System.out.println("Warm");
        } else if (grads >= 15 && grads <= 20) {
            System.out.println("Mild");
        } else if (grads >= 12 && grads <= 14.9) {
            System.out.println("Cool");
        } else if (grads >= 5 && grads <= 11.9) {
            System.out.println("Cold");
        } else {
            System.out.println("unknown");
        }

    }
}
