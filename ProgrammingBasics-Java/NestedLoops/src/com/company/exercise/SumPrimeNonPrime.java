package com.company.exercise;

import java.util.Scanner;

public class SumPrimeNonPrime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int sumPrime = 0, sumNonPrime = 0;

        String input = sc.nextLine();

        while(!input.equals("stop")) {

            int num = Integer.parseInt(input);

            if (num < 0) {
                System.out.println("Number is negative.");
                input = sc.nextLine();
                continue;
            }

            // find the prime - simpler
            boolean isPrime = true;

            for (int i = 2; i <= num/2; i++) {

                if (num % i == 0 || num % (i + 2) == 0) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                sumPrime += num;
            } else {
                sumNonPrime += num;
            }

            input = sc.nextLine();
        }

        System.out.println("Sum of all prime numbers is: "+sumPrime);
        System.out.println("Sum of all non prime numbers is: "+sumNonPrime);

    }
}
