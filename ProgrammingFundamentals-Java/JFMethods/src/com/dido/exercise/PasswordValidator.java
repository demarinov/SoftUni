package com.dido.exercise;

import java.util.Scanner;

public class PasswordValidator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String password = sc.nextLine();
        boolean isValid = hasValidPassword(password);

        if (isValid) {
            System.out.println("Password is valid");
        }
    }

    public static boolean hasValidPassword(String password) {

        boolean isValid = true;

        boolean isValidPassLen = checkPasswordLength(password);
        boolean hasValidLetters = checkLettersAndDigits(password);
        boolean hasValidDigits = checkForTwoDigits(password);

        isValid = isValidPassLen && hasValidLetters && hasValidDigits;
        return isValid;
    }

    public static boolean checkPasswordLength(String password) {

        if (password.length() >= 6 && password.length() <= 10) {

            return true;
        }

        System.out.println("Password must be between 6 and 10 characters");
        return false;
    }

    public static boolean checkLettersAndDigits(String password) {

        boolean isValid = false;

        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);

            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {

                isValid = true;
            } else if (c >= '0' && c<= '9') {
                isValid = true;
            } else  {
                System.out.println("Password must consist only of letters and digits");
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    public static boolean checkForTwoDigits(String password) {

        int countTwoDigits = 0;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (c >= '0' && c <= '9') {
                countTwoDigits++;
            }
        }

        if (countTwoDigits >= 2) {
            return true;
        }

        System.out.println("Password must have at least 2 digits");
        return false;
    }
}
