package com.dido.more;

import java.util.Scanner;

public class Messages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int groupOffset = 0;
        int letterOffset = 0;

        int nums = Integer.parseInt(sc.nextLine());
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= nums; i++) {

            String n = sc.nextLine();
            int len = n.length();
            int n1 = Integer.parseInt(String.format("%c",n.charAt(0)));

            // separate handling
            if (n1 == 1 || n1 == 0) {
                if (n1 == 0) {
                    result.append(' ');
                }
                continue;
            }

            groupOffset = (n1 - 2);
            letterOffset = len-1;
            int codeAscii = 0;

            if (n1 == 8 || n1 == 9) {
                codeAscii = 'a' + groupOffset*3 + 1 + letterOffset;
            } else {
                codeAscii = 'a' + groupOffset*3 + letterOffset;
            }
            char c = (char)(codeAscii);

            result.append(c);
        }

        System.out.print(result);
    }
}
