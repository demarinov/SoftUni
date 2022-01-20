package com.dido.more;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WinningTicket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] tickets = sc.nextLine().split("\\s*,\\s*");

        // invalid case: @@@@@@@@@@@@$$$$$$$$
        String regex = "(?=.{20}).*?([@]{6,}|[$]{6,}|[#]{6,}|[\\^]{6,}).*?(?<=.{10})\\1";

        Pattern pattern = Pattern.compile(regex);

        for (int i = 0; i < tickets.length; i++) {

            String ticket = tickets[i].trim();
            Matcher matcher = pattern.matcher(ticket);

            if (ticket.length() != 20) {
                System.out.println("invalid ticket");
                continue;
            }

            boolean ticketMatch = false;
            while(matcher.find()) {

                String symbols = matcher.group(1);
                ticketMatch = true;

                if (symbols.length() == 10) {
                    System.out.printf("ticket \"%s\" - %d%c Jackpot!%n",ticket, symbols.length(),symbols.charAt(0));
                } else if (symbols.length() >= 6 && symbols.length() <=9) {
                    System.out.printf("ticket \"%s\" - %d%c%n",ticket, symbols.length(),symbols.charAt(0));
                }

            }

            if (!ticketMatch) {
                System.out.println("ticket \""+ticket+"\" - no match");
            }

        }

    }
}
