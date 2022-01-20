package com.dido.main;

public class Main {

    public static void main(String[] args) {
        // write your code here


        int a = 5;
        int b = a++;
        int c =++a;
        System.out.println(c);

        String numStr = "1fkjgdj12";

        Integer num = extractFirstDigits(numStr,0);

        if (num != null) {
            System.out.println(num);
        }

    }

    public static Integer extractFirstAlphas(String str, int index) {

        StringBuilder builder = new StringBuilder();
        for (int i = index; i < str.length(); i++) {

            if (Character.isAlphabetic(str.charAt(i))) {
                builder.append(String.format("%c",str.charAt(i)));
                continue;
            }

            break;
        }

        if (!builder.toString().isEmpty()) {
            Integer num = Integer.parseInt(builder.toString());

            return num;
        }

        return null;
    }

    public static Integer extractFirstDigits(String numStr, int index) {

        StringBuilder builder = new StringBuilder();
        for (int i = index; i < numStr.length(); i++) {

            if (Character.isDigit(numStr.charAt(i))) {
                builder.append(String.format("%c",numStr.charAt(i)));
                continue;
            }

            break;
        }

        if (!builder.toString().isEmpty()) {
            Integer num = Integer.parseInt(builder.toString());

            return num;
        }

        return null;
    }

}
