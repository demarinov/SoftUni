package com.dido.more;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class PersonInfo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        Map<String, String> namesAgeMap = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {

            String text = sc.nextLine();

            Map.Entry entry = getNameAndAge(text);
            if (entry != null) {
                namesAgeMap.putIfAbsent((String) entry.getKey(), (String) entry.getValue());
            }
        }

        namesAgeMap.entrySet().stream()
                .map(e -> String.format("%s is %s years old.", e.getKey(), e.getValue()))
                .forEach(System.out::println);
    }

    public static Map.Entry<String,String> getNameAndAge(String text) {

        int startNameSepIdx = text.indexOf('@');
        int endNameSepIdx = text.indexOf('|');

        String name = "";
        if (startNameSepIdx >= 0 && endNameSepIdx >= 0) {
            name = text.substring(startNameSepIdx + 1, endNameSepIdx);
        }

        int startAgeSepIdx = text.indexOf('#');
        int endAgeSepIdx = text.indexOf('*');

        String age = "";
        if (startAgeSepIdx >= 0 && endAgeSepIdx >= 0) {
            age = text.substring(startAgeSepIdx + 1, endAgeSepIdx);
        }

        if (!name.isEmpty() && !age.isEmpty()) {
            return Map.entry(name, age);
        }

        return null;

    }
}
