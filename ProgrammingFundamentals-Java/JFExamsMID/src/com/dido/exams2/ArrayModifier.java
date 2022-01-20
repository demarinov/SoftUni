package com.dido.exams2;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = Arrays.stream(sc.nextLine().split("\\s"))
                .mapToInt(s -> Integer.parseInt(s))
                .toArray();

        String input = sc.nextLine();

        while(!input.equals("end")) {

            String[] commandData = input.split("\\s");

            if (commandData[0].equals("swap")) {

                int indexOne = Integer.parseInt(commandData[1]);
                int indexTwo = Integer.parseInt(commandData[2]);

                int temp = nums[indexOne];
                nums[indexOne] = nums[indexTwo];
                nums[indexTwo] = temp;
            } else if (commandData[0].equals("multiply")) {
                int indexOne = Integer.parseInt(commandData[1]);
                int indexTwo = Integer.parseInt(commandData[2]);

                nums[indexOne] *= nums[indexTwo];
            } else if (commandData[0].equals("decrease")) {

                for (int i = 0; i < nums.length; i++) {

                    nums[i] -= 1;
                }
            }

            input = sc.nextLine();
        }

        for (int i = 0; i < nums.length; i++) {

            System.out.printf("%d",nums[i]);
            if (i != nums.length - 1) {
                System.out.printf(", ");
            }


        }
    }
}
