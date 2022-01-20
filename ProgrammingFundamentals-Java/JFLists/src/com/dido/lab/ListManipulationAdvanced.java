package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationAdvanced {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        String commands = sc.nextLine();

        while (!commands.equalsIgnoreCase("end")) {

            String[] commandList = commands.split("\\s+");

            switch (commandList[0].toLowerCase()) {

                case "contains":
                    Integer num = Integer.parseInt(commandList[1]);
                    if (numbers.contains(num)) {
                        System.out.println("Yes");
                    } else {
                        System.out.println("No such number");
                    }
                    break;

                case "print":
                    String type = commandList[1];

                    if (type.equalsIgnoreCase("even")) {
                        printEven(numbers);
                    } else if (type.equalsIgnoreCase("odd")) {
                        printOdd(numbers);
                    }
                    break;
                case "get":
                    printSum(numbers);
                    break;
                case "filter":
                    String condition = commandList[1];
                    Integer number = Integer.parseInt(commandList[2]);
                    applyFilterAndPrint(numbers, condition, number);
                    break;
                default:
                    break;
            }

            commands = sc.nextLine();
        }
    }

    public static void applyFilterAndPrint(List<Integer> nums, String condition, Integer num) {

        switch (condition) {

            case ">":
                printGreaterThan(nums, num);
                break;
            case "<":
                printLessThan(nums, num);
                break;
            case ">=":
                printGreaterEqualThan(nums, num);
                break;
            case "<=":
                printLessEqualThan(nums, num);
                break;
            default:
                break;
        }
    }

    public static void printLessEqualThan(List<Integer> nums, Integer num) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i).intValue() <= num.intValue()) {
                System.out.printf("%d ", nums.get(i));
            }
        }
        System.out.println();
    }

    public static void printGreaterEqualThan(List<Integer> nums, Integer num) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i).intValue() >= num.intValue()) {
                System.out.printf("%d ", nums.get(i));
            }
        }
        System.out.println();
    }

    public static void printLessThan(List<Integer> nums, Integer num) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i).intValue() < num.intValue()) {
                System.out.printf("%d ", nums.get(i));
            }
        }

        System.out.println();
    }

    public static void printGreaterThan(List<Integer> nums, Integer num) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i).intValue() > num.intValue()) {
                System.out.printf("%d ", nums.get(i));
            }
        }

        System.out.println();
    }

    public static void printSum(List<Integer> nums) {

        int sum = 0;

        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }

        System.out.println(sum);
    }

    public static void printOdd(List<Integer> nums) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i) % 2 != 0) {
                System.out.printf("%d ", nums.get(i));
            }
        }

        System.out.println();
    }

    public static void printEven(List<Integer> nums) {

        for (int i = 0; i < nums.size(); i++) {

            if (nums.get(i) % 2 == 0) {
                System.out.printf("%d ", nums.get(i));
            }
        }

        System.out.println();
    }
}
