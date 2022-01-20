package com.dido.exercise;

import java.util.*;

public class AppendArrays {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> arrays = new ArrayList<>(Arrays.asList(sc.nextLine().split("\\|")));

        List<String> resultArray = new ArrayList<>();

        for (int i = arrays.size()-1; i >= 0; i--) {

            List<String> elements = Arrays.asList(arrays.get(i).split("\\s+"));
           // Collections.sort(elements);
            resultArray.addAll(elements);
        }



        for (int i = 0; i < resultArray.size(); i++) {
            resultArray.remove("");
            System.out.printf("%s ",resultArray.get(i));
        }

    }
}
