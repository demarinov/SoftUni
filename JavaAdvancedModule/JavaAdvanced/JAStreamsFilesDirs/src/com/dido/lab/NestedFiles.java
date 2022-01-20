package com.dido.lab;

import java.io.File;
import java.util.*;

public class NestedFiles {

    public static void main(String[] args) {

        File file = new File("C:\\Users\\deyan\\Documents\\SoftUni" +
                "\\JavaAdvanced\\May20\\04. Java-Advanced-Files-and-Streams-Lab-Resources" +
                "\\Files-and-Streams");

        Deque<File> dirs = new ArrayDeque();
        dirs.offer(file);

        List<String> dirList = new ArrayList<>();
        while(!dirs.isEmpty()) {

            File filePolled = dirs.poll();

            if (filePolled.isDirectory()) {
                dirList.add(filePolled.getName());
                File[] files = filePolled.listFiles();
                for (File fileEntry : files) {
                    dirs.offer(fileEntry);
                }
            }
        }

        // do not need parent
//        dirList.remove(0);
        dirList.forEach(System.out::println);
        System.out.printf("%d folders",dirList.size());
    }
}
