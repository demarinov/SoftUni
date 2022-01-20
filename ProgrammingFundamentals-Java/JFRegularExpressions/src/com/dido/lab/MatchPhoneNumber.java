package com.dido.lab;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchPhoneNumber {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String phones = sc.nextLine();

//        •	It starts with "+359"
//•	Then, it is followed by the area code (always 2)
//•	After that, it’s followed by the number itself:
//        o	The number consists of 7 digits (separated in two groups of 3 and 4 digits respectively).
//•	The different parts are separated by either a space or a hyphen ('-').

         String regex = "\\+359([ -])2\\1[0-9]{3}\\1[0-9]{4}\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phones);

        List<String> matchedPhones = new ArrayList<>();
        while(matcher.find()) {

            matchedPhones.add(matcher.group());
        }

        System.out.println(String.join(", ",matchedPhones));
    }
}
