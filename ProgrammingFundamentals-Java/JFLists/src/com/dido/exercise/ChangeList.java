package com.dido.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // split by special character for example space \s !!!!
        List<Integer> nums = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("end")) {

            if (command.toLowerCase().contains("delete")) {
                String[] elements = command.split("\\s+");

                if (elements.length == 2) {
                    removeElementOccurences(nums, Integer.parseInt(elements[1]));
                } else {
                    removeAllElements(nums);
                }
            } else if (command.toLowerCase().contains("insert")) {
                String[] argArray = command.split("\\s+");
                if (argArray.length == 3) {
                    Integer element = Integer.parseInt(argArray[1]);
                    int position = Integer.parseInt(argArray[2]);

                    if (position >= 0 && position < nums.size()) {

                        nums.add(position, element);
                    }
                }
            }

            command = sc.nextLine();
        }

        printList(nums);
    }

    public static void printList(List<Integer> nums) {

        for (int i = 0; i < nums.size(); i++) {

            System.out.printf("%d ",nums.get(i));
        }
    }

    public static void removeAllElements(List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {

            nums.remove(i);
            i--;
        }
    }

    public static void removeElementOccurences(List<Integer> nums, Integer element) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i).intValue() == element.intValue()) {
                nums.remove(element);
                i--;
            }
        }
    }
}
