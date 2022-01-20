package com.dido.exams4;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ShoppingList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> groceries = Arrays.stream(sc.nextLine().split("!"))
                .collect(Collectors.toList());


        String input = sc.nextLine();

        while(!input.equals("Go Shopping!")) {

            String[] commandData = input.split("\\s");
            String command = commandData[0];
            String item = commandData[1];

            switch(command) {

                case "Urgent":
                    if (!groceries.contains(item)) {
                        groceries.add(0,item);
                    }
                    break;
                case "Unnecessary":
                    if (groceries.contains(item)) {
                        groceries.remove(item);
                    }
                    break;

                case "Correct":
                    String newItem = commandData[2];

                    if (groceries.contains(item)) {

                        groceries.set(groceries.indexOf(item), newItem);
                    }
                    break;
                case "Rearrange":
                    if (groceries.contains(item)) {

                        groceries.remove(item);
                        groceries.add(item);
                    }
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        for (int i = 0; i < groceries.size(); i++) {

            String grocery = groceries.get(i);

            System.out.printf("%s",grocery);

            if (i != groceries.size()-1) {
                System.out.printf(", ");
            }
        }
    }
}
