package com.dido.exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmail {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // read input
        // extract emails in format <user>@<host>,
        //•	<user> is a sequence of letters and digits, where '.', '-' and '_' can appear between them.
        //o	Examples of valid users: "stephan", "mike03", "s.johnson", "st_steward",
        // "softuni-bulgaria", "12345".
        //o	Examples of invalid users: ''--123", ".....", "nakov_-", "_steve", ".info".
       // String regex = "(^|(?<=\\s))(([a-zA-Z0-9]+)([\\.\\-_]?)([A-Za-z0-9]+)(@)([a-zA-Z]+([\\.\\-][A-Za-z]+)+))(\\b|(?=\\s))";
        String regex = "(?<=\\s)([A-Za-z0-9]+[\\._\\-]?[A-Za-z0-9]+)@([A-Za-z]+[\\-]?[A-Za-z]+)([.][A-Za-z]+[\\-]?[A-Za-z]+){1,}(\\b|(?=\\s))";
        // split the input string by space and regex each phrase separately if needed.
        //•	<host> is a sequence of at least two words, separated by dots '.'.
        // Each word is sequence of letters and can have hyphens '-' between the letters.
        //o	Examples of hosts: "softuni.bg", "software-university.com",
        // "intoprogramming.info", "mail.softuni.org".
        //o	Examples of invalid hosts: "helloworld", ".unknown.soft.", "invalid-host-", "invalid-".
        //•	Examples of valid emails: info@softuni-bulgaria.org, kiki@hotmail.co.uk,
        // no-reply@github.com, s.peterson@mail.uu.net, info-bg@software-university.software.academy.
        //•	Examples of invalid emails: --123@gmail.com, …@mail.bg, .info@info.info,
        // _steve@yahoo.cn, mike@helloworld, mike@.unknown.soft., s.johnson@invalid-.

        String input = sc.nextLine();

        Pattern pattern = Pattern.compile(regex);

        String text = input;
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {

//            String name = matcher.group(1);
//            String domain = matcher.group(2);
//            String subDomain = matcher.group(3);
//
//            System.out.println(name + "@" + domain + subDomain);
            String email = matcher.group(0);
            System.out.println(email);
        }


    }
}
