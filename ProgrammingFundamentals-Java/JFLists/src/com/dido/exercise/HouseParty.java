package com.dido.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int commands = Integer.parseInt(sc.nextLine());
        List<String> guests = new ArrayList<String>();

        for (int i = 0; i < commands; i++) {

            String command = sc.nextLine();
            String name = command.split("\\s+")[0];

            if (command.toLowerCase().contains("is going")) {

                if (!guests.contains(name)) {
                    guests.add(name);
                } else {
                    System.out.printf("%s is already in the list!%n",name);
                }
            } else if (command.toLowerCase().contains("is not going")) {
                if (!guests.contains(name)) {
                    System.out.printf("%s is not in the list!%n",name);

                } else {
                    guests.remove(name);
                }
            }
        }

        for (int i = 0; i < guests.size(); i++) {

            System.out.println(guests.get(i));
        }
    }
}
