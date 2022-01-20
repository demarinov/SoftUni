package com.dido.exams5;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> inventory = Arrays.stream(sc.nextLine().split(",\\s"))
                .collect(Collectors.toList());

        String input = sc.nextLine();

        while(!input.equals("Craft!")) {

//•	"Collect - {item}" – Receiving this command, you should add the given item in your inventory.
// If the item already exists, you should skip this line.
//•	"Drop - {item}" – You should remove the item from your inventory, if it exists.
//•	"Combine Items - {oldItem}:{newItem}" – You should check if the old item exists, if so,
// add the new item after the old one. Otherwise, ignore the command.
//•	"Renew – {item}" – If the given item exists, you should change its position and put it last in
// your inventory.


            String[] commandData = input.split("\\s-\\s");

            String command = commandData[0];

            switch(command) {

                case "Collect":
                    String item = commandData[1];
                    if (!inventory.contains(item)) {
                        inventory.add(item);
                    }
                    break;
                case "Drop":
                    item = commandData[1];
                    if (inventory.contains(item)) {
                        inventory.remove(item);
                    }
                    break;
                case "Combine Items":
                    String[] items = commandData[1].split(":");
                    String oldItem = items[0];
                    String newItem = items[1];

                    if (inventory.contains(oldItem)) {
                        int index = inventory.indexOf(oldItem);

                        inventory.add(index+1,newItem);
                    }
                    break;
                case "Renew":
                    item = commandData[1];
                    if (inventory.contains(item)) {
                        inventory.remove(item);
                        inventory.add(item);
                    }
                    break;
                default:
                    break;

            }

            input = sc.nextLine();
        }

        System.out.println(inventory.toString().replaceAll("[\\[\\]]",""));
    }
}
