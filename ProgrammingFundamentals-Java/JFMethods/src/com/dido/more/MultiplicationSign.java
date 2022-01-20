package com.dido.more;

import java.util.Scanner;

public class MultiplicationSign {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double n1 = Double.parseDouble(sc.nextLine());
        double n2 = Double.parseDouble(sc.nextLine());
        double n3 = Double.parseDouble(sc.nextLine());

        printProductSign(n1,n2,n3);
    }

    public static void printProductSign(double n1, double n2, double n3) {

        double[] arr = new double[3];
        arr[0] = n1;
        arr[1] = n2;
        arr[2] = n3;
        boolean negative = false;
        boolean zero = false;

        // negative test + zero test
        int countNeg = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                countNeg++;
            }

            // zero test
            if (arr[i] == 0) {
                zero = true;
            }
        }

        if (countNeg == 2 || countNeg == 0) {
            negative = false;
        } else {
            negative = true;
        }

        if (!negative && !zero) {
            System.out.println("positive");
        } else if (zero) {
            System.out.println("zero");
        } else {
            System.out.println("negative");
        }
    }
}
