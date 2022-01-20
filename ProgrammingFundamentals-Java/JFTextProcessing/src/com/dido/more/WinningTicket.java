package com.dido.more;

import java.util.*;
import java.util.stream.Collectors;

public class WinningTicket {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // edge case $$@@@@@@@@$$@@@@@@@@

        List<String> tickets = Arrays.stream(sc.nextLine().split("\\s*,\\s*"))
                .collect(Collectors.toList());

        Map<String, Integer> winningLengthsLeft = new LinkedHashMap<>();
        Map<String, Integer> winningLengthsRight = new LinkedHashMap<>();

        resetWinnings(winningLengthsLeft, winningLengthsRight);

        for (int i = 0; i < tickets.size(); i++) {
            // for each ticket check if it is invalid 20 chars long and put into hash map
            // split each ticket into halves and inspect both left and right
            // get the number of winning symbols on the left side if any
            // get the number of winning symbols on the right side if any
            // check if there is match 6-9 len or 10 len or no match on both both sides
            // get each match or no match ticket into hash map
            // print the resulting ticket in the order given - invalid, no match, match6-9, match10

            String ticket = tickets.get(i).trim();

            if (ticket.length() != 20) {

                System.out.println("invalid ticket");
                continue;
            }

            int middleIdx = ticket.length()/2;

            String prevChar = "";
            // inspect left side
            for (int j = 0; j < middleIdx; j++) {

                String c = String.format("%c",ticket.charAt(j));

                if (winningLengthsLeft.containsKey(c)) {
                    if (c.equals(prevChar)) {
                        winningLengthsLeft.put(c, winningLengthsLeft.get(c) + 1);
                    } else {
                        int currentCount = winningLengthsLeft.get(c);
                        if (currentCount < 6) {
                            winningLengthsLeft.put(c, 1);
                        }
                    }
                }

                prevChar = c;
            }

            prevChar = "";
            // inspect right side
            for (int j = middleIdx; j < ticket.length(); j++) {

                String c = String.format("%c",ticket.charAt(j));

                if (winningLengthsRight.containsKey(c)) {
                    if (c.equals(prevChar)) {
                        winningLengthsRight.put(c, winningLengthsRight.get(c) + 1);
                    } else {
                        int currentCount = winningLengthsRight.get(c);
                        // reset old sym count if not needed.
                        if (currentCount < 6) {
                            winningLengthsRight.put(c, 1);
                        }
                    }
                }

                prevChar = c;

            }

            boolean match = false;
            // find match or no match tickets
            for (Map.Entry<String, Integer> entryLeft : winningLengthsLeft.entrySet()) {

                 String key = entryLeft.getKey();
                 Integer valueLeft = entryLeft.getValue();
                 Integer valueRight = winningLengthsRight.get(key);

                 int length = Math.min(valueLeft, valueRight);

                 if (length >= 6 && length<=10) {
                     // match
                     if (length < 10) {
                         System.out.printf("ticket \"%s\" - %d%s%n", ticket, length, key);
                     } else {
                         System.out.printf("ticket \"%s\" - %d%s Jackpot!%n", ticket, length, key);
                     }

                     match = true;
                     break;
                 }

            }

            if (!match) {
                System.out.printf("ticket \"%s\" - no match%n",ticket);
            }
            resetWinnings(winningLengthsLeft, winningLengthsRight);
        }


    }

    public static void resetWinningsRight(Map<String, Integer> winsRight) {

        winsRight.put("@",0);
        winsRight.put("#",0);
        winsRight.put("$",0);
        winsRight.put("^",0);

    }

    public static void resetWinningsLeft(Map<String, Integer> winsLeft) {

        winsLeft.put("@",0);
        winsLeft.put("#",0);
        winsLeft.put("$",0);
        winsLeft.put("^",0);

    }

    public static void resetWinnings(Map<String, Integer> winsLeft, Map<String, Integer> winsRight) {

        winsLeft.put("@",0);
        winsLeft.put("#",0);
        winsLeft.put("$",0);
        winsLeft.put("^",0);

        winsRight.put("@",0);
        winsRight.put("#",0);
        winsRight.put("$",0);
        winsRight.put("^",0);

    }

    public static char findSymbol(char[] winningSymbols, char symbol) {

        for (int i = 0; i < winningSymbols.length; i++) {

            char c = winningSymbols[i];

            if (c == symbol) {
                return symbol;
            }
        }

        return '\0';
    }

    public static class Ticket {

        private String status;
        private String ticketSymbol;



    }
}


