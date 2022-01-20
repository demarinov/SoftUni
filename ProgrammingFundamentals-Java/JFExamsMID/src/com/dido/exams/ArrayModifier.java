package com.dido.exams;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//•	“swap {index1} {index2}” take two elements and swap their places.
//•	“multiply {index1} {index2}” take element at the 1st index and multiply it with element at 2nd index. Save the product at the 1st index.
//•	“decrease” decreases all elements in the array with 1.

        int[] numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .mapToInt(s -> Integer.parseInt(s)).toArray();

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("end")) {

            String[] commandData = command.split("\\s+");

            if (commandData[0].equalsIgnoreCase("decrease")) {
                decreaseArrayElements(numbers);
            } else if (commandData[0].equalsIgnoreCase("swap")) {
                int indexOne = Integer.parseInt(commandData[1]);
                int indexTwo = Integer.parseInt(commandData[2]);
                swap(numbers, indexOne, indexTwo);
            } else if (commandData[0].equalsIgnoreCase("multiply")) {
                int indexOne = Integer.parseInt(commandData[1]);
                int indexTwo = Integer.parseInt(commandData[2]);
                multiply(numbers, indexOne, indexTwo);
            }

            command = sc.nextLine();
        }

        printNumbers(numbers);
    }

    public static void multiply(int[] numbers, int indexOne, int indexTwo) {

        numbers[indexOne] *= numbers[indexTwo];
    }

    public static void swap(int[] numbers, int indexOne, int indexTwo) {

        int temp = numbers[indexOne];
        numbers[indexOne] = numbers[indexTwo];
        numbers[indexTwo] = temp;
    }

    public static void printNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            System.out.printf("%d ", numbers[i]);
        }
    }

    public static void decreaseArrayElements(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] -= 1;
        }
    }
}
