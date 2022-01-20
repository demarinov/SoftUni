package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, String> contactsMap = new LinkedHashMap<>();
        while(!input.equals("search")) {

            String[] contactData = input.split("-");
            String name = contactData[0];
            String phoneNumber = contactData[1];

            contactsMap.put(name, phoneNumber);

            input = sc.nextLine();
        }

        input = sc.nextLine();

        while(!input.equalsIgnoreCase("stop")) {

            String searchName = input;

            if (contactsMap.containsKey(searchName)) {
                System.out.printf("%s -> %s%n",searchName, contactsMap.get(searchName));
            } else {
                System.out.printf("Contact %s does not exist.%n",searchName);
            }

            input = sc.nextLine();
        }
    }
}
