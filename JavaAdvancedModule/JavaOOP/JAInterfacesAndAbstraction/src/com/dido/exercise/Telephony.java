package com.dido.exercise;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Telephony {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> numbers = Arrays.stream(sc.nextLine().split("\\s"))
                .collect(Collectors.toList());
        List<String> sites = Arrays.stream(sc.nextLine().split("\\s"))
                .collect(Collectors.toList());

        SmartPhone smartPhone = new SmartPhone(numbers, sites);

        System.out.print(smartPhone.call());
        System.out.print(smartPhone.browse());

    }

    interface Callable {
        String call();
    }

    interface Browsable {
        String browse();
    }

    static class SmartPhone implements Callable, Browsable {

        private List<String> numbers;
        private List<String> sites;

        public SmartPhone(List<String> numbers, List<String> sites) {
            this.numbers = numbers;
            this.sites = sites;
        }

        @Override
        public String call() {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < numbers.size(); i++) {

                String number = numbers.get(i);

                try {
                    for (int j = 0; j < number.length(); j++) {

                        if (!Character.isDigit(number.charAt(j))) {
                            throw new ArithmeticException("Invalid number!");
                        }
                    }
                    result.append("Calling... " + number + "\n");
                } catch (ArithmeticException e) {
                    result.append(e.getLocalizedMessage() + "\n");
                }
            }

            return result.toString();
        }

        @Override
        public String browse() {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < sites.size(); i++) {

                String site = sites.get(i);

                try {
                    for (int j = 0; j < site.length(); j++) {

                        if (Character.isDigit(site.charAt(j))) {
                            throw new ArithmeticException("Invalid URL!");
                        }
                    }
                    result.append("Browsing: " + site + "!\n");
                } catch (ArithmeticException e) {
                    result.append(e.getLocalizedMessage() + "\n");
                }

            }

            return result.toString();
        }
    }
}
