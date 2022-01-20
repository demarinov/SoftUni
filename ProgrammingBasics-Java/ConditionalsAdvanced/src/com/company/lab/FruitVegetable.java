package com.company.lab;

import java.util.Scanner;

public class FruitVegetable {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String product = sc.nextLine();

        switch(product) {

            case "banana":
            case "kiwi":
            case "apple":
            case "cherry":
            case "lemon":
            case "grapes":
                System.out.println("fruit");
                break;
            case "cucumber":
            case "tomato":
            case "pepper":
            case "carrot":
                System.out.println("vegetable");
                break;
            default:
                System.out.println("unknown");
                break;
        }
    }
}
