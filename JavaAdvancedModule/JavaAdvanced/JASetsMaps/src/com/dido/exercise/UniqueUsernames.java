package com.dido.exercise;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());

        Set<String> usersSet = new LinkedHashSet<>();
        for (int i = 0; i < count; i++) {

            String user = sc.nextLine();

            usersSet.add(user);
        }

        usersSet.stream().forEach(System.out::println);
    }
}
