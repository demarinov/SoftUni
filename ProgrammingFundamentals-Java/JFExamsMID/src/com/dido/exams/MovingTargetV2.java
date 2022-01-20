package com.dido.exams;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// print Strike missed for every index out of range and remove as much as possible in range.
public class MovingTargetV2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        String command = sc.nextLine();

        while (!command.equalsIgnoreCase("end")) {

            String[] commandData = command.split("\\s+");

            String instruction = commandData[0];
            Integer index = Integer.parseInt(commandData[1]);
            String value = commandData[2];

            if (instruction.equalsIgnoreCase("shoot")) {
                shoot(targets, index, value);
            } else if (instruction.equalsIgnoreCase("add")) {
                add(targets, index, value);
            } else if (instruction.equalsIgnoreCase("strike")) {
                strike(targets, index, value);
            }


            command = sc.nextLine();
        }

        System.out.println(targets.toString()
                .replaceAll("[\\[\\] ]","").replaceAll(",","|"));
    }

    public static void strike(List<Integer> targets, int index, String radius) {
//        o	Remove the target at the given index and the ones before and after it
//        depending on the radius, if such exist. If any of the indices in the range is invalid print:
//        "Strike missed!" and skip this command.


        int endLeftIndex = index - Integer.parseInt(radius);
        int endRightIndex = index + Integer.parseInt(radius);

//        if (endRightIndex >= targets.size() || endLeftIndex < 0) {
//            System.out.println("Strike missed!");
//            return;
//        }

        int count = Integer.parseInt(radius);
        // go right and remove 1 2 3 4 5 ?
        for (int i = 0; i <= count; i++) {

            int newIndex = index;
            if (newIndex >= targets.size()) {
                System.out.println("Strike missed!");
                continue;
            }
            targets.remove(newIndex);
        }

        // go left and remove 1 2 3 4 5 ?

        for (int j = 1; j <= count; j++) {

            int newIndex = index -j;
            if (newIndex < 0) {
                System.out.println("Strike missed!");
                continue;
            }

            targets.remove(newIndex);
        }


    }

    public static void add(List<Integer> targets, int index, String value) {

//        o	Insert a target with the received value at the received index,
//        if it exist. If not, print: "Invalid placement!"

        if (index < 0 || index >= targets.size()) {
            System.out.println("Invalid placement!");
            return;
        }

        targets.add(index, Integer.parseInt(value));

    }

    public static void shoot(List<Integer> targets, int index, String power) {

//        o	Shoot the target at the index, if it exists by reducing its value
//        by the given power (integer value).A target is considered shot when its value reaches 0.
//        o	Remove the target, if it is shot.

        if (index < 0 || index >= targets.size()) {
            return;
        }

        int target = targets.get(index);
        int diff = target - Integer.parseInt(power);

        if (diff <= 0) {
            targets.remove(index);
            return;
        }

        targets.set(index, diff);
    }
}
