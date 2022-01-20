package com.dido.exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Map<String, String> registrations = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String[] commandData = sc.nextLine().split("\\s+");
            String command = commandData[0].toLowerCase();
            String user = commandData[1];

            if (command.equalsIgnoreCase("register")) {
                String plate = commandData[2];
                register(registrations, user, plate);
            } else if (command.equalsIgnoreCase("unregister")) {
                unregister(registrations, user);
            }
        }

        registrations.entrySet().stream().map(e -> String.format("%s => %s",e.getKey(),e.getValue()))
                .forEach(System.out::println);
    }

    public static void unregister(Map<String, String> registrations, String user) {

//        o	If the user is not present in the database, the system should print:
//        "ERROR: user {username} not found"
//        o	If the aforementioned check passes successfully, the system should print:
//        "{username} unregistered successfully"

        if (!registrations.containsKey(user)) {
            System.out.println("ERROR: user "+user+" not found");
        } else {
            registrations.remove(user);
            System.out.println(user+" unregistered successfully");
        }

    }

    public static void register(Map<String, String> registrations, String user, String plate) {

//        o	The system only supports one car per user at the moment,
//        so if a user tries to register another license plate, using the same username,
//        the system should print:
//        "ERROR: already registered with plate number {licensePlateNumber}"
//        o	If the aforementioned checks pass successfully, the plate can be registered, so the
//        system should print:
//        "{username} registered {licensePlateNumber} successfully"

        if (registrations.containsKey(user)) {
            System.out.println("ERROR: already registered with plate number "+plate);
        } else {
            registrations.put(user, plate);
            System.out.println(user+" registered "+plate+" successfully");
        }
    }
}
