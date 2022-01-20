package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FixEmails {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input  = sc.nextLine();

        Map<String, String> emailsMap = new LinkedHashMap<>();
        while(!input.equals("stop")) {

            String name = input;
            String email = sc.nextLine();

            if (validEmail(email)) {
                emailsMap.put(name, email);
            }

            input = sc.nextLine();
        }

        emailsMap.entrySet().stream()
                .forEach(e -> System.out.printf("%s -> %s%n",e.getKey(), e.getValue()));
    }

    public static boolean validEmail(String email) {

        String sufOne = "uk";
        String sufTwo = "us";
        String sufThree = "com";

        int lenOne = sufOne.length();
        int lenTwo = sufThree.length();

        int startIdx = email.length() - lenOne;
        String emailSuf = email.substring(startIdx);
        if (emailSuf.equals(sufOne) || emailSuf.equals(sufTwo)) {
            return false;
        }

        startIdx = email.length() - lenTwo;
        emailSuf = email.substring(startIdx);

        if (emailSuf.equals(sufThree)) {
            return false;
        }

        return true;
    }
}
