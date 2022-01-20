package com.dido.exams6;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Chat logger
public class Problem3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        List<String> chatMessages = new LinkedList<>();
        while(!input.equals("end")) {

            String[] commandData = input.split("\\s");

            switch(commandData[0]) {

                case "Chat":
                    String message = commandData[1];
                    chatMessages.add(message);
                    break;
                case "Delete":
                    String messageToDelete = commandData[1];

                    if (chatMessages.contains(messageToDelete)) {
                        chatMessages.remove(messageToDelete);
                    }
                    break;
                case "Edit":
                    String messageToEdit = commandData[1];
                    String editedMessage = commandData[2];

                    if (chatMessages.contains(messageToEdit)) {
                        int index = chatMessages.indexOf(messageToEdit);

                        chatMessages.set(index, editedMessage);
                    }
                    break;

                case "Pin":
                    String messageToMove = commandData[1];

                    if (chatMessages.contains(messageToMove)) {
                        chatMessages.remove(messageToMove);
                        chatMessages.add(messageToMove);
                    }
                    break;

                case "Spam":
                    for (int i = 1; i < commandData.length; i++) {
                        chatMessages.add(commandData[i]);
                    }
                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        chatMessages.stream().forEach(System.out::println);
    }
}
