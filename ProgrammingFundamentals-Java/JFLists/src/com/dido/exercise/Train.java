package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> wagons = Arrays.stream(sc.nextLine().split(" "))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        int maxCapacityPerWagon = Integer.parseInt(sc.nextLine());

        String command = sc.nextLine();

        while(!command.equals("end")) {

            if (command.contains("Add")) {
                int addPassengers = Integer.parseInt(command.split(" ")[1]);
                wagons.add(addPassengers);
            } else {
                int passengers = Integer.parseInt(command);

                checkForWagonFit(wagons, passengers, maxCapacityPerWagon);
            }

            command = sc.nextLine();
        }


        // print the list content
        for (int i = 0; i < wagons.size(); i++) {
            System.out.printf("%d ",wagons.get(i));
        }
    }

    public static void checkForWagonFit(List<Integer> wagons, int passengers, int maxCapacity) {

        for (int i = 0; i < wagons.size(); i++) {

            if (wagons.get(i) + passengers <= maxCapacity) {
                wagons.set(i, wagons.get(i) + passengers);
                break;
            }
        }
    }
}
