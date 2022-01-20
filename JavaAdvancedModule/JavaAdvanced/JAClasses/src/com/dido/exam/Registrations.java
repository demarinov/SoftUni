package com.dido.exam;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrations {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer n = Integer.parseInt(sc.nextLine());

        String regex = "(U\\$)(?<user>[A-Z][a-z]{2,})\\1(P@\\$)(?<pass>[A-Za-z_]{5,}\\d+)\\3";

        // valid user + pass when:
        // username surrounded by "U$"
        // username min 3 chars long
        // start with an upper, followed only by
        // lowercase letters
        // pass surrounded by "P@$"
        //pass start with min 5 alpha letters no digits
        // and must end with a digits

        // U$MichaelU$P@$asdqwe123P@$ - valid

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        int registrations = 0;

        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            matcher = pattern.matcher(input);

            boolean isValidRegistration = false;
            while(matcher.find()) {

                String user = matcher.group(2);
                String pass = matcher.group(4);

                System.out.printf("Registration was successful%n");
                System.out.printf("Username: %s, Password: %s%n",user, pass);
                registrations++;
                isValidRegistration = true;
            }

            if (!isValidRegistration) {
                System.out.printf("Invalid username or password%n");
            }
        }


        // if valid registration
        // print "Registration was successful"
        // Username: {user}, Password: {pass}
        // if not
        // print "Invalid username or password"
        // in the end print
        // print "Successful registrations: {succesRegsCount}"
        System.out.printf("Successful registrations: %d%n",registrations);
    }
}
