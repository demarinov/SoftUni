package com.dido.exercise;

import java.util.Scanner;

public class ValidUsernames {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] userNames = sc.nextLine().split(", ");

//        •	Has length between 3 and 16 characters
//•	Contains only letters, numbers, hyphens and underscores

        for (int i = 0; i < userNames.length; i++) {

            String userName = userNames[i];

            if (isUserNameValid(userName)) {
                System.out.println(userName);
            }
        }

    }

    public static boolean isUserNameValid(String user) {

        int length = user.length();
        if (length < 3 || length > 16) {
            return false;
        }

        for (int i = 0; i < user.length(); i++) {

            Character c = user.charAt(i);

            if (!Character.isDigit(c) && !Character.isLetter(c) && (c != '-' && c != '_')) {
                return false;
            }
        }

        return true;
    }
}
