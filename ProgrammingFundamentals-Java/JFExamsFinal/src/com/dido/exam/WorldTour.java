package com.dido.exam;

import java.util.Scanner;

public class WorldTour {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String cities = sc.nextLine();

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("travel")) {

            String[] commandData = command.split(":");

            String instruction = commandData[0];

            if (instruction.equalsIgnoreCase("add stop")) {

                int index = Integer.parseInt(commandData[1]);
                String value = commandData[2];

                cities = addCity(cities, index, value);

            } else if (instruction.equalsIgnoreCase("remove stop")) {

                int startIndex = Integer.parseInt(commandData[1]);
                int endIndex = Integer.parseInt(commandData[2]);

                cities = removeCity(cities, startIndex, endIndex);


            } else if (instruction.equalsIgnoreCase("switch")) {

                String oldCity = commandData[1];
                String newCity = commandData[2];

                cities = switchCity(cities, oldCity, newCity);
            }

            System.out.println(cities);
            command = sc.nextLine();
        }

        System.out.println("Ready for world tour! Planned stops: "+cities);
    }

    public static String switchCity(String cities, String oldCity, String newCity) {

        String result = cities.replaceAll(oldCity, newCity);
        return result;
    }

    public static String removeCity(String cities, int startIndex, int endIndex) {

        String result = "";

        if ((startIndex >= 0 && startIndex <= cities.length()-1)
                && (endIndex >=0 && endIndex <= cities.length()-1)) {
            result += cities.substring(0, startIndex);
            result += cities.substring(endIndex + 1);

            return result;
        }

        return cities;
    }

    public static String addCity(String cities, int index, String value) {

        String result = "";
        if (index >= 0 && index <= cities.length()-1) {

            result += cities.substring(0, index);
            result += value;
            result += cities.substring(index);

            return result;
        }

        return cities;
    }
}
