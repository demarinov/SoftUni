package com.dido.exams2;

import java.util.Arrays;
import java.util.Scanner;

public class ShootForTheWin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        String input = sc.nextLine();

        int shotTargets = 0;
        while(!input.equals("End")) {

            int index = Integer.parseInt(input);

            if (index < 0 || index >= nums.length || nums[index] == -1) {
                input = sc.nextLine();
                continue;
            }

            // shoot the target
            int targetValue = nums[index];
            nums[index] = -1;
            shotTargets++;

            // check values to the left
            for (int i = index-1; i >=0 ; i--) {

                int value = nums[i];

                if (value > targetValue) {
                    value -= targetValue;
                } else if (value <= targetValue && value != -1) {
                    value += targetValue;
                }

                nums[i] = value;
            }

            // check to the right
            for (int i = index+1; i < nums.length; i++) {

                int value = nums[i];

                if (value > targetValue) {
                    value -= targetValue;
                } else if (value <= targetValue && value != -1) {
                    value += targetValue;
                }

                nums[i] = value;
            }

            input = sc.nextLine();
        }

        System.out.printf("Shot targets: %d -> ",shotTargets);
        for (int i = 0; i < nums.length; i++) {
            System.out.printf("%d ",nums[i]);
        }
    }
}
