package com.dido.exercise;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class SimpleTextEditor {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        Deque<String> stateStack  = new ArrayDeque<>();
        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {

            String input = sc.nextLine();
            String currentCommand = input;


            if (currentCommand.charAt(0) == '1') {
                String text = currentCommand.split("\\s+")[1];
                stateStack.push(textBuilder.toString());
                textBuilder.append(text);
            } else if (currentCommand.charAt(0) == '2') {

                int count = Integer.parseInt(currentCommand.split("\\s+")[1]);
                if (textBuilder.length() == 0) {
                    continue;
                }
                stateStack.push(textBuilder.toString());
                if (count < textBuilder.length()) {
                    String newText = textBuilder.substring(0, textBuilder.length() - count);
                    textBuilder.delete(0, textBuilder.length());
                    textBuilder.append(newText);
                } else {
                    textBuilder.delete(0, count);
                }
            } else if (currentCommand.charAt(0) == '3') {

                int position = Integer.parseInt(currentCommand.split("\\s+")[1]);
                if (textBuilder.length() < position) {
                    continue;
                }
                char element = textBuilder.charAt(position-1);
                System.out.println(element);
            } else if (currentCommand.charAt(0) == '4') {


                if (!stateStack.isEmpty()) {
                    textBuilder.delete(0,textBuilder.length());
                    textBuilder.append(stateStack.pop());
                }
            }

        }

    }
}
