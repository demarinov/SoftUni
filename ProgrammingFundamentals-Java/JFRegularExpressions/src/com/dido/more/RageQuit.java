package com.dido.more;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RageQuit {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        // aSd2&5s@1
        String regex = "(\\D+)(\\d+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        Map<Character, Integer> symbolsRepetitions = new LinkedHashMap<>();
        List<String> repeatedSymbols = new ArrayList<>();
        while (matcher.find()) {

            String symbol = matcher.group(1).toUpperCase();
            Integer count = Integer.parseInt(matcher.group(2));

            if (count == 0) {
                continue;
            }

            checkSymbolRepetition(symbolsRepetitions, symbol);

            String repeatedSymbol = repeatSymbol(symbol, count);
            repeatedSymbols.add(repeatedSymbol);
        }

        long uniqueSymbols = symbolsRepetitions.entrySet().stream()
                .count();

        System.out.printf("Unique symbols used: %d%n", uniqueSymbols);

        repeatedSymbols.stream().map(s -> s).forEach(System.out::print);
    }

    public static void checkSymbolRepetition(Map<Character, Integer> symbols, String symbol) {

        for (int i = 0; i < symbol.length(); i++) {

            char c = symbol.charAt(i);

            symbols.putIfAbsent(c, 0);
            symbols.put(c, symbols.get(c) + 1);
        }
    }

    public static String repeatSymbol(String symbol, int count) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(symbol);
        }

        return builder.toString();
    }
}
