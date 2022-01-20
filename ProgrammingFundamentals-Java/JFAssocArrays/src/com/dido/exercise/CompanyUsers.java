package com.dido.exercise;

import java.util.*;
import java.util.stream.Collectors;

public class CompanyUsers {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        Map<String, List<String>> companies = new LinkedHashMap<>();

        while(!input.equalsIgnoreCase("end")) {

            String[] companyData = input.split(" -> ");
            String name = companyData[0];
            String id = companyData[1];

            if (companies.containsKey(name)) {
                List<String> ids = companies.get(name);
                if (!ids.contains(id)) {
                    ids.add(id);
                }
            } else {
                List<String> ids = new ArrayList<>();
                ids.add(id);
                companies.put(name, ids);
            }

            input = sc.nextLine();
        }

        List<Map.Entry<String, List<String>>> sortedCompanies = companies.entrySet()
                .stream()
                .sorted((c1,c2) -> c1.getKey().compareTo(c2.getKey()))
                .collect(Collectors.toList());

        for (Map.Entry<String, List<String>> company : sortedCompanies) {
            String name  = company.getKey();
            List<String> ids = company.getValue();

            System.out.println(name);

            ids.stream().map(id -> String.format("-- %s",id))
                    .forEach(System.out::println);
        }
    }
}
