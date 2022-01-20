package com.company.lab;

import java.util.Scanner;

public class MinNumber {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int minNum = 1000000000;
        String input = sc.nextLine();

        while(!input.equals("Stop")){

            int num = Integer.parseInt(input);
            if (minNum > num) {
                minNum = num;
            }

            input = sc.nextLine();
        }

        System.out.println(minNum);


    }
}
