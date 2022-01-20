package com.dido.exams;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShootForTheWin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> targets = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        String command = sc.nextLine();
        int countShots = 0;

        while(!command.equalsIgnoreCase("end")) {

            int index = Integer.parseInt(command);

            if (index < 0 || index >= targets.size()) {
                command = sc.nextLine();
                continue;
            }

            // check index value, shoot target
            Integer value = targets.get(index);
            targets.set(index, -1);
            countShots++;

            // go to the right and apply rules
            for (int i = index; i < targets.size(); i++) {

                int target = targets.get(i);
                if (target == -1) {
                    continue;
                }

                if (value >= target) {
                    targets.set(i, target+value);
                } else {
                    targets.set(i, target - value);
                }
            }

            // go to the left and apply rules
            for (int i = index; i >= 0; i--) {

                int target = targets.get(i);
                if (target == -1) {
                    continue;
                }

                if (value >= target) {
                    targets.set(i, target+value);
                } else {
                    targets.set(i, target - value);
                }
            }

            command = sc.nextLine();
        }

        System.out.println("Shot targets: "
                +countShots+" -> "+targets.toString().replaceAll("[\\[\\],]",""));

    }
}
