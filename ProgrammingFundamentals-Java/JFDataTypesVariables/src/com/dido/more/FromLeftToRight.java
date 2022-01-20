package com.dido.more;

import java.util.Scanner;

public class FromLeftToRight {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());


        for (int i = 0; i < n; i++) {

            String str = sc.nextLine();
            int sepIdx = str.indexOf(' ');
            String s1 = str.substring(0,sepIdx);
            String s2 = str.substring(sepIdx+1,str.length());
            Double n1 = Double.parseDouble(s1);
            Double n2 = Double.parseDouble(s2);
            int sumOfDigits = 0;

            if (n1 > n2) {
                // sum of left digits

                double num = Math.abs(n1);

                while(num != 0) {

                    double rem = num % 10;
                    sumOfDigits += rem;
                    num /= 10;
                }
            } else {
                // sum of right digits
                double num = Math.abs(n2);
                while(num != 0) {

                    double rem = num % 10;
                    sumOfDigits += rem;
                    num /= 10;
                }
            }

            System.out.println(sumOfDigits);
        }
    }
}
