package com.dido.more;

import java.util.Scanner;

public class DecryptingMessages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int key = Integer.parseInt(sc.nextLine());
        int n = Integer.parseInt(sc.nextLine());
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 1; i <=n; i++) {

            String str = sc.nextLine();

            char c = (char) (str.charAt(0) + key);
            strBuilder.append(String.format("%c",c));
        }

        System.out.print(strBuilder.toString());
    }
}
