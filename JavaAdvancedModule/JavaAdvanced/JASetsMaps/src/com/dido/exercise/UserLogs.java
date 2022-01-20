package com.dido.exercise;

import java.util.*;

public class UserLogs {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

//        IP=(IP.Address) message=(A&sample&message) user=(username)

//        username:
//        IP => count, IP => count.

        String input = sc.nextLine();


        Map<String, Map<String, List<String>>> logMap = new TreeMap<>();
        while(!input.equals("end")) {

            String[] logInfo = input.split("\\s");

            String[] ipInfo = logInfo[0].split("=");
            String[] messageInfo = logInfo[1].split("=");
            String[] userInfo = logInfo[2].split("=");

            String ip = ipInfo[1];
            String message = messageInfo[1];
            String user = userInfo[1];

            if (!logMap.containsKey(user)) {

                Map<String, List<String>> ipMessageMap = new LinkedHashMap<>();
                List<String> messageList =  new ArrayList<>();
                messageList.add(message);
                ipMessageMap.put(ip, messageList);
                logMap.put(user, ipMessageMap);
            } else {
                Map<String, List<String>> ipMessageMap = logMap.get(user);

                if (ipMessageMap.containsKey(ip)) {
                    List<String> messageList = ipMessageMap.get(ip);
                    messageList.add(message);
                } else {
                    List<String> messageList =  new ArrayList<>();
                    messageList.add(message);
                    ipMessageMap.put(ip, messageList);
                }

            }

            input = sc.nextLine();
        }

        logMap.entrySet().stream()
                .map(e ->
                {
                    String user = e.getKey();
                    String values = e.getValue().entrySet().stream()
                            .map(v ->
                                    {
                                        String ip =  v.getKey();
                                        long countMessages = v.getValue().stream().count();

                                        return String.format("%s => %d, ",ip, countMessages);
                                    })
                            .reduce("", String::concat);
                    
                    return String.format("%s:%n%s.%n",user, values.substring(0,values.length()-2));
                })
                .forEach(System.out::print);

    }
}
