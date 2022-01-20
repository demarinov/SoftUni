package com.dido.lab;

import java.util.Scanner;

public class ForeignLang {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String country = sc.nextLine();

        switch (country) {

            case "USA":
            case "England":
                System.out.printf("English");
                break;
            case "Spain":
            case "Argentina":
            case "Mexico":
                System.out.printf("Spanish");
                break;

            default:
                System.out.printf("unknown");
                break;
        }
    }
}
