package com.company.lab;

import java.util.Scanner;

public class MaxNumber {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int maxNum = -1000000000;
        String input = sc.nextLine();

        while(!input.equals("Stop")){

            int num = Integer.parseInt(input);
            if (maxNum < num) {
                maxNum = num;
            }

            input = sc.nextLine();
        }

        System.out.println(maxNum);


    }
}
