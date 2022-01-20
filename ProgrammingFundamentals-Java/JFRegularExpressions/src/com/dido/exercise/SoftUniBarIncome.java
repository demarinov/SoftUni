package com.dido.exercise;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftUniBarIncome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        String regex = "%([A-Z][a-z]+)%[^$|.%]*?<(\\w+)>[^$|.%]*?\\|(\\d+)\\|[^$|.%]*?([0-9]+[.]?[0-9]*?)\\$";

        String input = sc.nextLine();

        Pattern pattern = Pattern.compile(regex);
        double totalIncome = 0.0d;
        while(!input.equalsIgnoreCase("end of shift")) {
            Matcher matcher = pattern.matcher(input);

            // The parts of a valid order should appear in the
            // order given: customer, product, count and a price.
            while(matcher.find()) {

                String customer = matcher.group(1);
                String product = matcher.group(2);
                String count = matcher.group(3);
                String price = matcher.group(4);

                boolean isValid = true;
                if (customer == null || customer.isEmpty()) {

                    isValid = false;
                }

                if (product == null || product.isEmpty()) {
                    isValid = false;
                }

                if (count == null || count.isEmpty()) {
                    isValid = false;
                }

                if (price == null || price.isEmpty()) {
                    isValid = false;
                }

                if (isValid) {
                    double totalPrice = Double.parseDouble(price) * Integer.parseInt(count);
                    System.out.printf("%s: %s - %.2f%n",customer,product,totalPrice);
                    totalIncome += totalPrice;
                }
            }

            input = sc.nextLine();
        }

        System.out.printf("Total income: %.2f",totalIncome);

    }
}
