package com.dido.more;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DrumSet {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Double savings = Double.parseDouble(sc.nextLine());
        List<Integer> drumSetInitial = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> drumSet = new ArrayList<>(drumSetInitial);

        while(true) {

            String command = sc.nextLine();

            if (command.equalsIgnoreCase("Hit it again, Gabsy!")) {
                break;
            }

            int hitPower = Integer.parseInt(command);

            savings = hitTheDrum(drumSet, savings, hitPower, drumSetInitial);

        }

        System.out.println(drumSet.toString().replaceAll("[\\[\\],]",""));
        System.out.printf("Gabsy has %.2flv.",savings);
    }

    public static Double hitTheDrum(List<Integer> drumSet, Double savings, int hitPower, List<Integer> dsInitial) {


        for (int i = 0; i < drumSet.size(); i++) {

            int drumState = drumSet.get(i) - hitPower;

            if (drumState <= 0) {

                // replace drum
                double price = dsInitial.get(i) * 3;
                double priceDiff = savings - price;
                if (price > savings) {
                    drumSet.remove(i);
                    dsInitial.remove(i);
                    i--;
                } else {
                    savings = priceDiff;
                    drumSet.set(i, dsInitial.get(i));
                }

            } else {
                drumSet.set(i, drumState);
            }

        }

        return savings;
    }
}
