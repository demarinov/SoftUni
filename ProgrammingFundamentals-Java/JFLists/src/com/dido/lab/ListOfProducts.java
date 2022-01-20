package com.dido.lab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ListOfProducts {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = Integer.parseInt(sc.nextLine());
        List<String> numList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            String product = sc.nextLine();

            numList.add(product);
        }

        Collections.sort(numList);

        for (int i = 0; i < numList.size(); i++) {

            System.out.printf("%d.%s%n",i+1,numList.get(i));
        }
    }
}
