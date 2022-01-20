package com.company.lab;

import java.util.Scanner;

public class VowelsSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = sc.nextLine();
        int sum = 0;

        // буква	a	e	i	o	u
        // стойност	1	2	3	4	5

        for (int i = 0; i < text.length(); i++) {

            switch(text.charAt(i)) {

                case 'a':
                    sum +=1;
                    break;
                case 'e':
                    sum += 2;
                    break;
                case 'i':
                    sum +=3;
                    break;
                case 'o':
                    sum +=4;
                    break;
                case 'u':
                    sum +=5;
                    break;
                default:
                    break;

            }

        }

        System.out.println(sum);

    }
}
