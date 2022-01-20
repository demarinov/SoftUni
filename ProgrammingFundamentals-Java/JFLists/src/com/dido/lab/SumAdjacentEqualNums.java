package com.dido.lab;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SumAdjacentEqualNums {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Double> numbers = Arrays.stream(sc.nextLine().split("\\s+"))
                .map(s -> Double.parseDouble(s)).collect(Collectors.toList());

        for (int i = 0; i < numbers.size()-1; i++) {

            if (numbers.get(i).intValue() == numbers.get(i+1).intValue()) {
                double sum = numbers.get(i).doubleValue() + numbers.get(i+1).doubleValue();
                numbers.set(i,sum);
                numbers.remove(i+1);
                i=-1;
            }
        }

        String output = getNumbers(numbers);
        System.out.println(output);
    }

    public static String getNumbers(List<Double> numbers) {

        String output = "";
        for (int i = 0; i < numbers.size(); i++) {

            output += new DecimalFormat("0.#").format(numbers.get(i)) + " ";
        }

        return output;
    }
}
