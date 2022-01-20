package com.dido.more;

import java.util.*;
import java.util.stream.Collectors;

public class MixedUpList {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> listOne = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> listTwo = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Integer.parseInt(s)).collect(Collectors.toList());

        List<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < Math.min(listOne.size(), listTwo.size()); i++) {

            resultList.add(listOne.get(i));
            resultList.add(listTwo.get(listTwo.size() - 1 - i));
        }

        int upperRange = -1;
        int lowerRange = -1;
        // get the ranges
        if (listOne.size() > listTwo.size()) {
            upperRange = listOne.get(listOne.size()-2);
            lowerRange = listOne.get(listOne.size()-1);
        } else if (listTwo.size() > listOne.size()) {
            upperRange = listTwo.get(0);
            lowerRange = listTwo.get(1);
        }

        if (upperRange < lowerRange) {
            int temp = upperRange;
            upperRange = lowerRange;
            lowerRange = temp;
        }

        for (int i = 0; i < resultList.size(); i++) {

            int num = resultList.get(i).intValue();
            if (num <= lowerRange || num >= upperRange) {
                resultList.remove(i);
                i--;
            }
        }

        Collections.sort(resultList);
        System.out.println(resultList.toString().replaceAll("[\\[\\],]",""));
    }
}
