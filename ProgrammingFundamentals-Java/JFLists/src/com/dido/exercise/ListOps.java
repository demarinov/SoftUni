package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOps {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        String command = sc.nextLine();

        while(!command.equalsIgnoreCase("end")) {

            String[] commandInfo = command.split("\\s+");
            String op = commandInfo[0];

            if (op.equalsIgnoreCase("add")) {
                Integer number = Integer.parseInt(commandInfo[1]);
                numbers.add(number);
            } else if (op.equalsIgnoreCase("insert")) {
                Integer number = Integer.parseInt(commandInfo[1]);
                int idx = Integer.parseInt(commandInfo[2]);

                if (idx >= 0 && idx < numbers.size()) {
                    numbers.add(idx,number);
                } else {
                    System.out.println("Invalid index");
                }
            } else if (op.equalsIgnoreCase("shift")) {
                String subOp = commandInfo[1];

                if (subOp.equalsIgnoreCase("left")) {

                    int count = Integer.parseInt(commandInfo[2]);

                    for (int i = 1; i <= count; i++) {

                        Integer firstNum = numbers.get(0);
                        // shift left start from index 1 to end
                        for (int j = 1; j < numbers.size(); j++) {
                            numbers.set(j-1, numbers.get(j));

                            if (j+1 == numbers.size()) {
                                numbers.set(j, firstNum);
                                firstNum = numbers.get(0);
                            }
                        }
                    }
                } else if (subOp.equalsIgnoreCase("right")) {
                    int count = Integer.parseInt(commandInfo[2]);

                    for (int i = 1; i <= count; i++) {

                        Integer lastNum = numbers.get(numbers.size()-1);
                        // shift right start from index size-2 to beginning
                        for (int j = numbers.size()-2; j >= 0; j--) {
                            numbers.set(j+1, numbers.get(j));

                            if (j == 0) {
                                numbers.set(j, lastNum);
                                lastNum = numbers.get(numbers.size()-1);
                            }
                        }
                    }
                }

            } else if (op.equalsIgnoreCase("remove")) {
                int idx = Integer.parseInt(commandInfo[1]);

                if (idx >= 0 && idx < numbers.size()) {
                    numbers.remove(idx);
                } else {
                    System.out.println("Invalid index");
                }
            }

            command = sc.nextLine();
        }

        for (int i = 0; i < numbers.size(); i++) {

            System.out.printf("%d ",numbers.get(i));
        }
    }
}
