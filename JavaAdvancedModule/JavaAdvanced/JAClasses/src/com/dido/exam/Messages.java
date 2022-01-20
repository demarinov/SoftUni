package com.dido.exam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Messages {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Integer capacity = Integer.parseInt(sc.nextLine());
        String input = sc.nextLine();

        Map<String, Integer> sentMessages = new LinkedHashMap<>();
        Map<String, Integer> receivedMessages = new LinkedHashMap<>();

        while(!"Statistics".equals(input)) {

            String[] commandData = input.split("=");

            switch(commandData[0]) {

                case "Add":
                    // {user}{sent}{received}
                    // if person exists ignore the line
                    // otherwise add it to sent/received
                    String user = commandData[1];
                    Integer sent = Integer.parseInt(commandData[2]);
                    Integer received = Integer.parseInt(commandData[3]);

                    if (!sentMessages.containsKey(user) && !receivedMessages.containsKey(user)) {
                        sentMessages.put(user, sent);
                        receivedMessages.put(user, received);
                    }

                    break;
                case "Message":
                    // {sender}{receiver}
                    // if both exist increase by 1
                    // if capacity reached
                    // remove first sender and print
                    // {user} reached the capacity!
                    String sender = commandData[1];
                    String receiver = commandData[2];

                    if (sentMessages.containsKey(sender) && receivedMessages.containsKey(receiver)) {
                        sentMessages.put(sender, sentMessages.get(sender)+1);
                        receivedMessages.put(receiver, receivedMessages.get(receiver)+1);

                        Integer totalCountSender = sentMessages.get(sender) + receivedMessages.get(sender);

                        if (totalCountSender >= capacity) {
                            sentMessages.remove(sender);
                            receivedMessages.remove(sender);
                            System.out.printf("%s reached the capacity!%n",sender);
                        }

                        Integer totalCountReceiver = sentMessages.get(receiver) + receivedMessages.get(receiver);
                        if (totalCountReceiver >= capacity) {
                            receivedMessages.remove(receiver);
                            sentMessages.remove(receiver);
                            System.out.printf("%s reached the capacity!%n",receiver);
                        }
                    }

                    break;
                case "Empty":
                    // {username}
                    // if exists delete his records
                    // if "All" given delete all records
                    user = commandData[1];

                    if ("All".equals(user)) {
                        sentMessages.clear();
                        receivedMessages.clear();
                    } else {

                        if (sentMessages.containsKey(user)) {
                            sentMessages.remove(user);
                        }

                        if (receivedMessages.containsKey(user)) {
                            receivedMessages.remove(user);
                        }
                    }

                    break;
                default:
                    break;
            }

            input = sc.nextLine();
        }

        // print the count of users
        // each person with his messages - send and received
        // sorted in desc by received msgs and then by
        // user in asc

        int count = sentMessages.size();
        System.out.printf("Users count: %d%n",count);
        sentMessages.entrySet().stream()
                .sorted((e1,e2) -> {
                    String userOne = e1.getKey();
                    String userTwo = e2.getKey();
                    Integer receivedMsgOne = 0;
                    if (receivedMessages.containsKey(userOne)) {
                        receivedMsgOne = receivedMessages.get(userOne);
                    }

                    Integer receivedMsgTwo = 0;
                    if (receivedMessages.containsKey(userTwo)) {
                        receivedMsgTwo = receivedMessages.get(userTwo);
                    }

                    int result = receivedMsgTwo.compareTo(receivedMsgOne);

                    return result == 0 ? userOne.compareTo(userTwo): result;
                })
                .forEach(e ->{
                    String user = e.getKey();
                    Integer countSent = e.getValue();
                    Integer countReceived = 0;

                    if (receivedMessages.containsKey(user)) {
                        countReceived = receivedMessages.get(user);
                    }
                    System.out.printf("%s - %d%n",e.getKey(), (countSent + countReceived));
                });



        // Users count: {count}
        // {username} - {messages}
    }
}
