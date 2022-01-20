package com.dido.more;

import java.util.Arrays;
import java.util.Scanner;

public class EncryptSortPrint {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int num = Integer.parseInt(sc.nextLine());
        int[] encrypt = new int[num];


        for (int i = 0; i < num; i++) {

            String name = sc.nextLine();
            int sum = 0;

//            •	The code of each vowel multiplied by the string length
//            •	The code of each consonant divided by the string length
//            Sort the number sequence in ascending order and print it on the console.

            for (int j = 0; j < name.length(); j++) {

                char c = name.charAt(j);


                switch(c) {

                    case 'a':
                    case 'e':
                   // case 'y':
                    case 'u':
                    case 'i':
                    case 'o':
                    case 'A':
                    case 'E':
                        // case 'y':
                    case 'U':
                    case 'I':
                    case 'O':
                        //The code of each vowel multiplied by the string length
                        sum += (c * name.length());
                        break;
                    default:
                        // The code of each consonant divided by the string length
                        sum += (c / name.length());
                        break;


                }
            }

            encrypt[i] = sum;

        }

      Arrays.sort(encrypt);

        for (int i = 0; i < encrypt.length; i++) {

            System.out.printf("%d%n",encrypt[i]);
        }
        ;
    }
}
