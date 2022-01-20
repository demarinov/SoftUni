package com.company.more;

import java.util.Scanner;

public class TheSongOfTheWheels {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int m = Integer.parseInt(sc.nextLine());
        boolean foundForth = false;
        int count = 0;
        String pass = "";

        for (int a = 1; a <=9; a++) {
            for (int b = 1; b <=9; b++) {
                for (int c = 1; c <=9; c++) {
                    for (int d = 1; d <=9; d++) {

                        // a*b + c*d = m; a<b, c>d

                        if (a >= b || c <= d) {
                            continue;
                        }

                        if ((a *b) + (c * d) == m) {
                            System.out.print(""+a+b+c+d+" ");
                            count++;
                        }

                        if (count == 4 && foundForth == false) {
                            foundForth = true;
                            pass = String.format("%d%d%d%d",a,b,c,d);

                        }
                    }

                }
            }

        }


        System.out.println();

        if (foundForth) {
            System.out.println("Password: "+pass);
        } else {
            System.out.println("No!");
        }
    }
}
