package com.dido.exercise;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.DoubleConsumer;
import java.util.function.UnaryOperator;

public class AppliedArithmetic {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        UnaryOperator<Double> addOp = s -> s + 1;
        UnaryOperator<Double> multiplyOp = s -> s * 2;
        UnaryOperator<Double> subtractOp = s -> s - 1;

        DoubleConsumer printConsumer = null;

        String input = sc.nextLine();
        double[] nums = Arrays.stream(input.split("\\s"))
                .mapToDouble(s -> Double.parseDouble(s))
                .toArray();

        input = sc.nextLine();

        while(!"end".equals(input)) {

            switch(input) {

                case "add":
                    for (int i = 0; i < nums.length; i++) {

                        Double result = addOp.apply(nums[i]);
                        nums[i] = result;
                    }
                    break;
                case "multiply":
                    for (int i = 0; i < nums.length; i++) {

                        Double result = multiplyOp.apply(nums[i]);
                        nums[i] = result;
                    }
                    break;
                case "subtract":
                    for (int i = 0; i < nums.length; i++) {

                        Double result = subtractOp.apply(nums[i]);
                        nums[i] = result;
                    }
                    break;
                case "print":

                    printConsumer =s -> {
                        DecimalFormat format = new DecimalFormat("#.###");
                        System.out.printf("%s ",format.format(s));
                    };
                    Arrays.stream(nums).forEach(printConsumer);
                    System.out.println();
                    break;
            }


            input = sc.nextLine();
        }

//        if (printConsumer != null) {
//            Arrays.stream(nums).forEach(printConsumer);
//            System.out.println();
//        }

    }
}
