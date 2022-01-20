package com.dido.more;

import java.lang.reflect.AnnotatedArrayType;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TakeSkipRope {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        List<String> list = new ArrayList<>();
        List<Integer> numList = new ArrayList<>();
        List<String>  nonNumList = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            char c = input.charAt(i);

            switch(c) {

                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    numList.add(Integer.parseInt(String.format("%c",c)));
                    break;
                default:
                    nonNumList.add(String.format("%c",c));
                    break;

            }
        }

//        System.out.println(numList.toString());
//        System.out.println(nonNumList.toString());

        List<Integer> takeList = new ArrayList<>();
        List<Integer> skipList = new ArrayList<>();

        for (int i = 0; i < numList.size(); i++) {

            if (i % 2 == 0) {
                takeList.add(numList.get(i));
            } else {
                skipList.add(numList.get(i));
            }
        }

        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < takeList.size(); i++) {

            int takeCount = takeList.get(i);
            String result = "";

            for (int j = 0; j < Math.min(takeCount, nonNumList.size()); j++) {

                result += nonNumList.get(j);
                nonNumList.remove(j);
                takeCount--;
                j--;
            }

            resultList.add(result);

            int skipCount = skipList.get(i);

            for (int j = 0; j < Math.min(skipCount, nonNumList.size()); j++) {
                nonNumList.remove(j);
                skipCount--;
                j--;
            }
        }

        String result = "";

        for (int i = 0; i < resultList.size(); i++) {

            result += resultList.get(i);
        }

        System.out.println(result);
    }
}
