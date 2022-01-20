package com.dido.more;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarRace {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> times = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());


        int midPoint = times.size() / 2;
        double leftTime = 0.0d;

        // calculate total time on the left
        for (int i = 0; i < midPoint; i++) {

            double time = Math.abs(times.get(i));

            if (time == 0) {
                leftTime = leftTime * 0.8;
            } else {
                leftTime += time;
            }
        }


        double rightTime = 0.0d;
        // calculate total time on the right
        for (int i = times.size()-1; i >= midPoint + 1; i--) {

            double time = Math.abs(times.get(i));

            if (time == 0) {
                rightTime = rightTime * 0.8;
            } else {
                rightTime += time;
            }
        }

        if (leftTime < rightTime) {
            System.out.printf("The winner is left with total time: %.1f",leftTime);
        } else if (rightTime < leftTime) {
            System.out.printf("The winner is right with total time: %.1f ",rightTime);
        }
    }
}
