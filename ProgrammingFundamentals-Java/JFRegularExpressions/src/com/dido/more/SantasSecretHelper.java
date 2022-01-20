package com.dido.more;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SantasSecretHelper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        String input = sc.nextLine();

        String regex = "@([A-Za-z]+)[^@!>:\\-]*!([GN])!";
        Pattern pattern = Pattern.compile(regex);


        while(!input.equalsIgnoreCase("end")) {

            String decodedMsg = decodeMsg(input, n);

            Matcher matcher = pattern.matcher(decodedMsg);

            while(matcher.find()) {

                String name = matcher.group(1);
                String behaviour = matcher.group(2);

                if (behaviour.equalsIgnoreCase("g")) {
                    System.out.println(name);
                }
            }

            input = sc.nextLine();
        }
    }

    public static String decodeMsg(String input, int n) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);
            char newChar =(char)(c - n);

            builder.append(String.format("%c",newChar));
        }

        return builder.toString();
    }
}
