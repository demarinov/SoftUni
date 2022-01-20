package com.dido.more;

import java.util.Scanner;

public class LDEnglishName {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());

        int digit = num % 10;
        String msg = "";

        switch(digit) {

            case 0:
                msg = "zero";
                break;
            case 1:
                msg = "one";
                break;
            case 2:
                msg = "two";
                break;
            case 3:
                msg = "three";
                break;
            case 4:
                msg = "four";
                break;
            case 5:
                msg = "five";
                break;
            case 6:
                msg = "six";
                break;
            case 7:
                msg = "seven";
                break;
            case 8:
                msg = "eight";
                break;
            case 9:
                msg = "nine";
                break;
            default:
                break;

        }

        System.out.print(msg);
    }
}
