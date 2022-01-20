package com.dido.lab;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListManipulationBasics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());


        String commands = sc.nextLine();

        while(!commands.equalsIgnoreCase("end")) {

            String[] commandList = commands.split("\\s+");

            switch(commandList[0].toLowerCase()) {

                case "add":
                    addNumber(numbers, Integer.parseInt(commandList[1]));
                    break;
                case "remove":
                    removeNumber(numbers, Integer.parseInt(commandList[1]));
                    break;
                case "removeat":
                    removeNumberAt(numbers, Integer.parseInt(commandList[1]));
                    break;
                case "insert":
                    insertNumber(numbers,Integer.parseInt(commandList[1]),Integer.parseInt(commandList[2]));
                    break;
                default:
                    break;
            }

            commands = sc.nextLine();
        }

        System.out.println(numbers.toString().replaceAll("[\\[\\],]",""));
    }

    public static void addNumber(List<Integer> numbers, Integer num) {
        numbers.add(num);
    }

    public static void removeNumber(List<Integer> numbers, Integer num) {
        numbers.remove(num);
    }

    public static void removeNumberAt(List<Integer> numbers, int index) {
        numbers.remove(index);
    }

    public static void insertNumber(List<Integer> numbers, Integer num, int index) {
        numbers.add(index,num);
    }
}
