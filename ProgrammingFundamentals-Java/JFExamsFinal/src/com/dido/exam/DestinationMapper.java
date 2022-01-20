package com.dido.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String destinations = sc.nextLine();

        Pattern pattern = Pattern.compile("([=/][A-Z][a-zA-Z]+[=/])");
        Matcher matcher = pattern.matcher(destinations);

        List<String> destinationList = new ArrayList<>();

        while(matcher.find()) {

            String value = matcher.group(1);

            char start = value.charAt(0);
            char end = value.charAt(value.length()-1);

            if (start != end) {
                continue;
            }

            value = value.substring(1,value.length()-1);
            destinationList.add(value);
//            System.out.println(value);
        }

        int travelPoints = 0;
        for (int i = 0; i < destinationList.size(); i++) {

            travelPoints += destinationList.get(i).length();
        }

        System.out.println("Destinations: "+destinationList.toString().replaceAll("[\\[\\]]",""));
        System.out.println("Travel Points: "+travelPoints);

    }
}
