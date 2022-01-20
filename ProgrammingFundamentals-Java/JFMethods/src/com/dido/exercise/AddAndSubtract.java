package com.dido.exercise;

import java.util.Scanner;

public class AddAndSubtract {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = Integer.parseInt(sc.nextLine());
        int n2 = Integer.parseInt(sc.nextLine());
        int n3 = Integer.parseInt(sc.nextLine());

        int sumTwo = addNumbers(n1, n2);
        int result = subtractNumbers(sumTwo, n3);

        System.out.println(result);

    }

    public static int addNumbers(int n1, int n2) {
        return (n1 + n2);
    }

    public  static int subtractNumbers(int n1, int n2) {
        return (n1 - n2);
    }


}
