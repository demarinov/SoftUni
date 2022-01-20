package com.dido.lab;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniDMParty {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        Set<String> vip = new TreeSet<>();
        Set<String> regular = new TreeSet<>();

        String input = sc.nextLine();

        while(!input.equals("PARTY")) {

            String guest = input;

            if (Character.isDigit(guest.charAt(0))) {
                // VIP
                if (!vip.contains(guest)) {
                    vip.add(guest);
                }
            } else {
                // regular
                if (!regular.contains(guest)) {
                    regular.add(guest);
                }
            }

            input = sc.nextLine();
        }

        input = sc.nextLine();
        while(!input.equals("END")) {

            String incomingGuest = input;

            if (vip.contains(incomingGuest)) {
                vip.remove(incomingGuest);
            }

            if (regular.contains(incomingGuest)) {
                regular.remove(incomingGuest);
            }

            input = sc.nextLine();
        }

        System.out.println(""+(vip.size()+regular.size()));
        if (!vip.isEmpty()) {
            vip.stream().forEach(System.out::println);
        }

        if (!regular.isEmpty()) {
            regular.stream().forEach(System.out::println);
        }
    }
}
