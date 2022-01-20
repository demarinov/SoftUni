package com.dido.lab;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchDates {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String dates = sc.nextLine();


//•	Always starts with two digits, followed by a separator
//•	After that, it has one uppercase and two lowercase letters (e.g. Jan, Mar).
//•	After that, it has a separator and exactly 4 digits (for the year).
//•	The separator could be either of three things: a period (“.”), a hyphen (“-“) or a forward slash (“/”)
//•	The separator needs to be the same for the whole date (e.g. 13.03.2016 is valid, 13.03/2016 is NOT).
// Use a group backreferrence to check for this.

        String regex = "(?<day>\\d{2})([.\\-\\/])(?<month>[A-Z][a-z]{2})\\2(?<year>\\d{4})";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dates);

        while(matcher.find()) {

            String day = matcher.group("day");
            String month = matcher.group("month");
            String year = matcher.group("year");

            System.out.printf("Day: %s, Month: %s, Year: %s%n",day, month, year);
        }
    }
}
