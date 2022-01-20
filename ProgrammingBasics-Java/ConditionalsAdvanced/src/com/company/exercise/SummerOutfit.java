package com.company.exercise;

import java.util.Scanner;

public class SummerOutfit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int grads = Integer.parseInt(sc.nextLine());
        String dayTime = sc.nextLine();

        String outfit ="", shoes="";

        if (dayTime.equals("Morning")) {

            if (grads >= 10 && grads <= 18) {

                outfit = "Sweatshirt";
                shoes ="Sneakers";
            } else if (grads > 18 && grads <=24) {

                outfit ="Shirt";
                shoes ="Moccasins";

            } else if (grads >= 25) {
                outfit ="T-Shirt";
                shoes ="Sandals";
            }

        } else if (dayTime.equals("Afternoon")) {

            if (grads >= 10 && grads <= 18) {

                outfit ="Shirt";
                shoes ="Moccasins";

            } else if (grads > 18 && grads <=24) {

                outfit ="T-Shirt";
                shoes ="Sandals";

            } else if (grads >= 25) {
                outfit ="Swim Suit";
                shoes ="Barefoot";
            }

        } else if (dayTime.equals("Evening")) {

            if (grads >= 10 && grads <= 18) {
                outfit ="Shirt";
                shoes ="Moccasins";

            } else if (grads > 18 && grads <=24) {
                outfit ="Shirt";
                shoes ="Moccasins";

            } else if (grads >= 25) {
                outfit ="Shirt";
                shoes ="Moccasins";
            }

        }

        System.out.println("It's " +
                grads +
                " degrees, get your " +
                outfit +
                " and " +
                shoes +
                ".");
    }
}
