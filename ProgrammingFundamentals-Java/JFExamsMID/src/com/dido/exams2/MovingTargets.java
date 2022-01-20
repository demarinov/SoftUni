package com.dido.exams2;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovingTargets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(sc.nextLine().split("\\s"))
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        while(!input.equals("End")) {

            String[] targetInfo = input.split("\\s");

            String command = targetInfo[0];
            int index = Integer.parseInt(targetInfo[1]);
            int value = Integer.parseInt(targetInfo[2]);

            if (command.equals("Shoot")) {

                if (index >= 0 && index < targets.size()) {
                    targets.set(index,targets.get(index)-value);

                    if (targets.get(index) <= 0) {
                        // shot
                        targets.remove(index);
                    }
                }
            } else if (command.equals("Add")) {

                if (index >= 0 && index < targets.size()) {

                    targets.add(index, value);
                } else {
                    System.out.println("Invalid placement!");
                }
            } else if (command.equals("Strike")) {

                if (index-value >= 0 && index+value < targets.size()) {

                    // strike right
                    for (int i = 1; i <= value; i++) {
                        targets.remove(index+1);
                    }

                    // remove target
                    targets.remove(index);

                    // go left
                    for (int i = 1; i <= value; i++) {
                        targets.remove(index-1);
                    }

                } else {
                    System.out.println("Strike missed!");
                }
            }

            input = sc.nextLine();
        }

        for (int i = 0; i < targets.size(); i++) {

            System.out.printf("%d", targets.get(i));

            if (i != targets.size()-1) {
                System.out.printf("|");
            }

        }
    }
}
